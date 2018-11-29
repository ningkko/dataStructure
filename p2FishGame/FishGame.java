package edu.smith.cs.csc212.p2;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class FishGame {
    /**
     * This is the world in which the fish are missing. (It's mostly a List!).
     */
    World world;
    /**
     * The player (a Fish.COLORS[0]-colored fish) goes seeking their friends.
     */
    Fish player;
    FishHome home;
    /**
     * These are the missing fish!
     */
    List<Fish> missing;
    // fish found
    List<Fish> found;
    // fish returned
    List<Fish> returned;
    List<Food> food;
    List<Bubble> bubble;
    int stepsTaken;
    int score;
    int fishNum;
    
    /**
     * Create a FishGame of a particular size.
     * @param w how wide is the grid?
     * @param h how tall is the grid?
     */
    public FishGame(int w, int h) {
        world = new World(w, h);
        
        fishNum=0;
        returned = new ArrayList<Fish>();
        missing = new ArrayList<Fish>();
        found = new ArrayList<Fish>();
        food=new ArrayList<Food>();
        bubble=new ArrayList<Bubble>();
        
        // Add a home!
        home = world.insertFishHome();
        
        int rockNumber=15;
        for (int i=0; i<rockNumber; i++) {
            world.insertRockRandomly();
        }
        
        int fallingRockNumber=10;
        for (int i=0; i<fallingRockNumber; i++) {
            world.insertFallingRockRandomly();
        }
        
        world.insertSnailRandomly();
        world.insertSnailRandomly();
        // Make the player out of the 0th fish color.
        player = new Fish(0, world);
        // Start the player at "home".
        player.setPosition(home.getX(), home.getY());
        player.markAsPlayer();
        world.register(player);
        
        // Generate fish of all the colors but the first into the "missing" List.
        for (int ft = 1; ft < Fish.COLORS.length; ft++) {
            Fish friend = world.insertFishRandomly(ft);
            missing.add(friend);
            fishNum+=1;
        
        }
    }
    
    
    /**
     * How we tell if the game is over: if missingFishLeft() == 0.
     * @return the size of the missing list.
     */
    public int missingFishLeft() {
        return missing.size();
    }
    
    /**
     * This method is how the PlayFish app tells whether we're done.
     * @return true if the player has won (or maybe lost?).
     */
    public boolean gameOver() {
        if(returned.size()==fishNum) {
            player.setPosition(home.getX(),home.getY());
            return true;
        }
        return false;
    }
    
    /**
     * Add score if finds a fish
     */
    public void score(WorldObject wo){
        
        if(((Fish) wo).getColor()==Color.pink) {
            score += 100;
        }else if (((Fish) wo).getColor()==Color.red){
            score += 80;
        }else if(((Fish) wo).getColor()==Color.white) {
            score += 60;
        }else {
            score += 10;
        }
    }
    /**
     * Update positions of everything (the user has just pressed a button).
     */
    public void step() {
        // Keep track of how long the game has run.
        this.stepsTaken += 1;
        Random rand = ThreadLocalRandom.current();
        if (rand.nextDouble() < .08) {
            food.add(world.insertFoodRandomly());
        }
        
        if (rand.nextDouble() < .08) {
            bubble.add(world.insertBubbleRandomly());
        }      
        
        // check if any fish steps onto a bubble
        // loop thru all bubbles
        for(int i=0;i<bubble.size();i++) {
            List<WorldObject> thingsPassingBubble = this.bubble.get(i).findSameCell();
            for (WorldObject thing : thingsPassingBubble) {
    
                // if at any point the player passes bubble, the bubble will be destroyed
                if (thing.isPlayer()) {
                    // remove the bubble
                    world.remove(bubble.get(i));
                    bubble.remove(bubble.get(i));
                    // for both fish in found and missing, set bubbleState to false
                    for(Fish f:missing) {
                        if(f.inSameSpot(thing)) {
                            f.bubbleState=false;
                        }
                    }
                    for(Fish f:found) {
                        if(f.inSameSpot(thing)) {
                            f.bubbleState=false;
                        }
                    }
                // else if a missing fish steps onto it, it gets trapped
                }else if(missing.contains(thing)||found.contains(thing)){
                    Fish fish = (Fish) thing;
                    fish.bubbleState = true;
                }
            }
        }
        // check if anyone eats the food
        // loop thru all food
        for(int i=0;i<food.size();i++) {
            List<WorldObject> thingsPassingFood = this.food.get(i).findSameCell();
            for (WorldObject thing : thingsPassingFood) {
    
                // if at any point the player passes food, add 5 points
                if (thing.isPlayer()) {
                    this.score+=5;
                    world.remove(food.get(i));
                    food.remove(food.get(i));

                // else if a missing fish or a snail eats it, no point
                }else if(missing.contains(thing)||(thing instanceof Snail)){
                    world.remove(food.get(i));
                    food.remove(food.get(i));
                }
            }
        }
        
        // Check what is passing by the fish home
        List<WorldObject> thingsPassingHome = this.home.findSameCell();
        for (WorldObject thing : thingsPassingHome) {

            // if at any point the player passes home, all fish it found return home
            int foundFishNum=found.size();
            if (thing.isPlayer()) {
                for(int i=foundFishNum-1;i>=0;i--) {
                    Fish fish=found.get(i);
                    world.remove(fish);
                    returned.add(fish);
                    found.remove(fish);
                    
                }
            }else if(missing.contains(thing)){
                returned.add((Fish)thing);
                world.remove(thing);
                missing.remove(thing);
            }
        }
        // These are all the objects in the world in the same cell as the player.
        List<WorldObject> overlap = this.player.findSameCell();
        // The player is there, too, let's skip them.
        overlap.remove(this.player);
        
        // if total steps>20, fish after the second position has a 20%percent chance of missing
        if (this.stepsTaken>20) {
            if (found.size()>=3) {

                if (rand.nextDouble() < 0.3) {
                    int foundFishNum=found.size();
                    for (int i=foundFishNum-1;i>=2;i-- ) {
                        missing.add(found.get(i));
                        found.remove(found.get(i));

                    }
                }
            }
        }
        
        // If we find a fish, remove it from missing.
        for (WorldObject wo : overlap) {
            

            // It is missing if it's in our missing list.
            if (missing.contains(wo)) {
                // Remove this fish from the missing list.
                missing.remove(wo);
                
                // Remove from world.
                found.add((Fish) wo);
                World.objectsFollow(player, found);
                
                // Increase score when you find a fish!
                this.score(wo);
                
            }
        }
        
        // Make sure missing fish *do* something.
        
        wanderMissingFish();
        // When fish get added to "found" they will follow the player around.
        World.objectsFollow(player, found);
        // Step any world-objects that run themselves.
        world.stepAll();
    }
    
    /**
     * Call moveRandomly() on all of the missing fish to make them seem alive.
     */
    private void wanderMissingFish() {
        Random rand = ThreadLocalRandom.current();
        for (Fish lost : missing) {
            if(lost.bubbleState==false) {
                // 30% of the time, lost fish move randomly.
                if (lost.fastScared==1){
                    if (rand.nextDouble() < 0.3) {
                        lost.moveRandomly();
                    }
                }else {
                // 80% of the time, lost fast-scared fish move randomly.
                    if (rand.nextDouble() < 0.8) {
                        lost.moveRandomly();
                    }
                }
            }
        }
    }

    /**
     * This gets a click on the grid. We want it to destroy rocks that ruin the game.
     * @param x - the x-tile.
     * @param y - the y-tile.
     */
    public void click(int x, int y) {

    	List<WorldObject> atPoint = world.find(x, y);
        
        // loop thru all objects at the clicked cell.
        for(int i=0; i<atPoint.size();i++) {
            
            WorldObject wo=atPoint.get(i);
            if(wo instanceof Rock||wo instanceof FallingRock||wo instanceof Bubble){
               // if it's a bubble
            	if(wo instanceof Bubble) {
                	// find the fish at the cell
                	List<WorldObject> objects=wo.findSameCell();
                	for(WorldObject object:objects) {
	                	if(object instanceof Fish) {
	                		Fish fishObject=(Fish)object;
	                		
	                		//change its bubbleState to false
	                		fishObject.bubbleState=false;
	                	}
                	}
                }
            	
                // remove the bubble
                world.remove(wo); 
                bubble.remove(wo);

            }
        }
    } 
}

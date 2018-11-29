package edu.smith.cs.csc212.p4;
import java.util.HashMap;
import java.util.Map;
/**
 * @author Ning
 */

public class TheHourglassSanatorium implements GameWorld{
    private Map<String, Place> places = new HashMap<>();
    
    
    /**
     * Constructor that build the game.
     */
    public TheHourglassSanatorium() {

        //secret door

        Place entranceHall = insert(
				Place.create("entranceHall", "You are in the grand entrance hall of a large building.\n"
						+ "The front door is locked. How did you get here?"));
		entranceHall.addExit(new Exit("basement", "There are stairs leading down."));
		entranceHall.addExit(new Exit("attic", "There are stairs leading up."));
		entranceHall.addExit(new LockedExit("kitchen", "There is a red door.", "key1"));
		
		Place basement = insert(
				Place.create("basement", "You have found the basement of the mansion.\n" + 
		                           "It is darker down here.\n" +
						"You get the sense a secret is nearby, but you only see the stairs you came from."
						));
		basement.addExit(new Exit("entranceHall", "There are stairs leading up."));
        Key key1=new Key("key1","This is a shimmering key you found at the basement.","There's something shimmering in a pile of dust");
        basement.addItem(key1, 1);
        
		Place attic = insert(Place.create("attic",
				"Something rustles in the rafters as you enter the attic. Creepy.\n" + "It's big up here."));
		attic.addExit(new Exit("entranceHall", "There are stairs leading down."));
		attic.addExit(new Exit("attic2", "There is more through an archway"));

		Place attic2 = insert(Place.create("attic2", "There's definitely a bat in here somewhere.\n"
				+ "This part of the attic is brighter, so maybe you're safe here."));
		attic2.addExit(new Exit("attic", "There is more back through the archway"));
		attic2.addExit(new Exit("fishroom","There is a small door with only half of it above the floor."));
		Battery battery=new Battery("battery","This is a battery you found in the attic.","You stepped onto something hard..");
		attic2.addItem(battery,1);
		Torch torch=new Torch("torch","This is a torch you found in the attic.","You see a torch.");
		attic2.addItem(torch, 1);
		attic2.addExit(new SecretExit("window","There's a window which seems to be open."));
		
		Place window=insert(Place.create("window", 
										"You found a window. Through which you see the grassland vaguely.. \n"+
										"It's definitely in the dawn. Or sunset.",
										"You found a window. Through which you see the grassland vaguely.. \n" + 
										"It's dark outside."));
		window.addExit(new Exit("grassland","Jump off the window."));
		window.addExit(new Exit("attic2","Leave the window."));
		
		Place grassland=insert(Place.terminal("grassland", "You jumped off the window."
				+ "When you were in the air you suddenly remembered a scene in which somebody falling for almost a century long."
				+ "You looked down and found that it was not a grassland, but an endless sea, and there was no mansion anywhere.."));
		
		Place fishroom = insert(Place.create("fishroom", "You slide in.\nYou can't really see anything, but you discern a smell of rotten animals. \n"
				+"You think it's the smell of dead fish.\nThe wall is sticky and wet, but you noticed a switch shimmering on it.\n"
				+ "There is also a decrepit dumbwaiter on the right. You don't know if it's still functioning."));
		fishroom.addExit(new Exit("attic2","Climb back to the attic."));
		fishroom.addExit(new Exit("basement","Take the dumbwaiter."));
		fishroom.addExit(new Exit("fishroom2","Flick the switch"));
		
	
		
		Place fishroom2 = insert(Place.create("fishroom2", "The light goes on.\n"
				+ "This room is massive that you doubt where could it possibly be in the building.\n"
				+ "You see a long table far away, covered by massive rotten dead fishes with maggots crawling on them and falling down.\n"
				+ "You think the red fluid on the wall is fish blood."));
		fishroom2.addExit(new Exit("basement","Take the dumbwaiter."));
		// key 2
		Key key2=new Key("key2","This is a key you found in a dead fish.","There might be something in those fish...");
		fishroom2.addItem(key2, 1);
		Food goodFish=new Food("fish","These fish are raw and rotten...How can you....","You feel disgusted.. But you know without food you can't survive..",false);
		Food badFish=new Food("fish","These fish are raw and rotten...How can you....",null,true);
		fishroom2.addItem(goodFish, 10);
		fishroom2.addItem(badFish,15);
		
		Place kitchen = insert(Place.create("kitchen", "You've found the kitchen. You smell old food and some kind of animal."));
		kitchen.addExit(new Exit("entranceHall", "There is a red door."));
		kitchen.addExit(new Exit("dumbwaiter", "Take the dumbwaiter."));
		Phone phone=new Phone("phone", "This is an old style phone. You wonder who would use it..", "There is a phone on the shelf, near a cooking book.");
		kitchen.addItem(phone, 1);
		
		Place dumbwaiter = insert(Place.create("dumbwaiter", "You crawl into the dumbwaiter. What are you doing?"));
		dumbwaiter.addExit(new SecretExit("secretRoom", "There's an inconspicuous button with a down arrow on it. You think it might take you to the bottom."));
		dumbwaiter.addExit(new Exit("kitchen", "Take it to the middle-level."));
		dumbwaiter.addExit(new Exit("attic2", "Take it up to the top."));
		
		Place secretRoom = insert(Place.create("secretRoom", "You have found the secret room."));
		secretRoom.addExit(new Exit("hallway0", "There is a long hallway."));
		secretRoom.addExit(new SecretExit("wardrobeRoom", "You noticed that there is a broken area on the floor."));
		secretRoom.addExit(new LockedExit("nowhere","There is a locked small door.","key2"));
		
		Place wardrobeRoom=insert(Place.create("wardrobeRoom", "This is a room with clothes piling up together. It has an intensive smell of washed shirts."
				+ "You feel sleepy..","There is no light in this room.."));
		wardrobeRoom.addExit(new Exit("secretRoom","Go back to the secret room.."));
		
		Place noWhere = insert(Place.create("noWhere", "You opened the door, and inside is endless darkness. But you feel this way must lead to some secret place.."));
		noWhere.addExit(new Exit("basement","There's even deeper.. It's getting more and more vent-like."));
		
		
		
		int hallwayDepth = 3;
		int lastHallwayPart = hallwayDepth - 1;
		for (int i=0; i<hallwayDepth; i++) {
			Place hallwayPart = insert(Place.create("hallway"+i,String.format("This is a very long hallway. You wrote %s on the wall to remind yourself that you've been here.",i+1)));
			if (i == 0) {
				hallwayPart.addExit(new Exit("secretRoom", "Go back."));
			} else {
				hallwayPart.addExit(new Exit("hallway"+(i-1), "Go back."));
			}
			if (i != lastHallwayPart) {
				hallwayPart.addExit(new Exit("hallway"+(i+1), "Go forward."));
			} else {
				hallwayPart.addExit(new Exit("crypt", "There is darkness ahead."));
			}
		}
		
		Place crypt = insert(Place.create("crypt", "You have found the crypt.\n"
				+"There are many books scattered on the floor around a candle. Apparently someone was studying something here."));
		UselessItems book=new UselessItems("book","These books are all written in ancient languages.", "These books might be useful.. They are always useful in movies at least.");
		crypt.addItem(book, 10);
        crypt.addExit(new Exit("secretRoom","Go back to the secret room"));
        // Make sure your graph makes sense!
        //checkAllExitsGoSomewhere();
    }

    /**
     * This helper method saves us a lot of typing. We always want to map from p.id
     * to p.
     * 
     * @param p - the place.
     * @return the place you gave us, so that you can store it in a variable.
     */
    private Place insert(Place p) {
        places.put(p.getId().toLowerCase(), p);
        return p;
    }

    /**
     * I like this method for checking to make sure that my graph makes sense!
     */
    private void checkAllExitsGoSomewhere() {
        boolean missing = false;
        // For every place:
        for (Place p : places.values()) {
            // For every exit from that place:
            for (Exit x : p.getExits()) {
                // That exit goes to somewhere that exists!
                if (!places.containsKey(x.getTarget())) {
                    // Don't leave immediately, but check everything all at once.
                    missing = true;
                    // Print every exit with a missing place:
                    System.err.println("Found exit pointing at " + x.getTarget() + " which does not exist as a place.");
                }
            }
        }
        
        // Now that we've checked every exit for every place, crash if we printed any errors.
        if (missing) {
            throw new RuntimeException("You have some exits to nowhere!");
        }
    }

    /**
     * Get a Place object by name.
     */
    public Place getPlace(String id) {
        return this.places.get(id);     
    }
}

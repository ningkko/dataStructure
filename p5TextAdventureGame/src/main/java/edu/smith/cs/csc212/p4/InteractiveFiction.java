package edu.smith.cs.csc212.p4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Calendar;

public class InteractiveFiction {


    /**
     * This is where we play the game.
     * @param args
     */
    public static void main(String[] args) {
        
    	// if the game is going on
    	boolean gameStatus=true;
    	
        // This is a text input source (provides getUserWords() and confirm()).
        TextInput input = TextInput.fromArgs(args);

        // This is the time
        GameTime time = new GameTime();
            
        // This is the game we're playing.
        GameWorld game = new TheHourglassSanatorium();
        
        // The player
        Player player=new Player("Nene the Default", 9999, "outside");
        
        // The character lose 2 HP per action
        int deltaHP = 2;
        
        // Initialize player location
        String place = player.getStartPlace();
        Place here=game.getPlace(place.toLowerCase());
        System.out.println(player.getName()+": \n"+here.getDescription(time));
        // get all possible exits
        List<Exit> exits = here.getExits();
        
        // print out also the item descriptions in the room.
        ArrayList<Items> placeItems = here.showItems();
      
        // for later storing delta t
        double timeEclipsed = 0;
        
        // instance of calendar
        Calendar cal = Calendar.getInstance();

        // actual hours and minutes
        int startMinute = cal.get(Calendar.MINUTE);
        int startSecond = cal.get(Calendar.SECOND);

        System.out.println("\n-------------------Menu-------------------\n"
				 	+ "quit | items | exits | search | pick | HP \n"
                + "------------------------------------------");
        /**
         * Logics ::=
         * 
         * 1. Time increaces
         * 2. check if player.HP too low
         *      -> End
         * 3. Check if end state 
         * 4. player.action
         *      check item
         *          use
         *          combine
         *          discard
         *          destroy
         *      enter room
         *          print new place info
         *      check place info
         *      pick up items
         *      search
         *          print out new exit info
         * 5. Process player input
         */
        while(true) {
        	
        	//update time
        	cal = Calendar.getInstance();
            timeEclipsed=(
            			(cal.get(Calendar.MINUTE)-startMinute)*60+
            			 cal.get(Calendar.SECOND)-startSecond)/60;
            time.setHour((int)timeEclipsed+time.getHours());
            player.loseHP((int)(timeEclipsed));
            time.isNewDay();
            time.remindNewDay();
            
            // Game over i1f the payer has a 0 HP.
            if(player.getHP()<=0) {
                System.out.println(String.format("You are too tired. You died.\n"
                    +"This is the end of your adventure. You have spent %s days %s hours in the game.",time.getDays(),time.getHours()));
                break;
            }
            else {
    
                // end the game if the player arrives at a terminal place or the status is false
                if (here.isTerminalState()||gameStatus==false) {
                    System.out.println(String.format("This is the end of your adventure. You have spent %s days %s hours in the game.",time.getDays(),time.getHours()));
                    break;
                }
                // Continue the game   
                else {           
                    
                    // Figure out what the user wants to do.
                    List<String> action = input.getUserWords(">");

                    // if q, quit game
                    if (action.contains("quit")) {
                        if (input.confirm("Are you sure you want to quit?")) {
                            break;
                        } else {
                            continue;
                        }
                    }

                    // if "item"
                    else if(action.contains("item")||action.contains("items")){

                    		// print out item info
                            boolean hasItem=player.checkItems();         
                            // print out item info
                            if(hasItem) {
                            	System.out.println("\n-----------------Menu-------------------\n"
                            					+ " (item name) => check | quit \n"
                       				 			+ "----------------------------------------");
                                
                                
                                // get a list of player.items
                                Map<Items,Integer> itemNums=player.getItems();
                                
                                while (true) {
                                	
                                    // update time in the loop
                                	cal = Calendar.getInstance();
                                    timeEclipsed=(
                                    			(cal.get(Calendar.MINUTE)-startMinute)*60+
                                    			 cal.get(Calendar.SECOND)-startSecond)/60;
                                    time.setHour((int)timeEclipsed+time.getHours());
                                    player.loseHP((int)(timeEclipsed));
                                    time.isNewDay();
                                    time.remindNewDay();
                                    
                                    // get input item name
                                    List<String> action2 = input.getUserWords(">");
                                    String action2string=action2.get(0).toLowerCase();
                                    // if q, break
                                    if(action2string.equals("quit")) {
                                    	
                                        System.out.println("\n-------------------Menu-------------------\n"
                            				 	+ "quit | items | exits | search | pick | HP \n"
                                            + "------------------------------------------");
                                        break;
                                    }
                                    
                                    // if no such item found
                                    else if(player.findItemByName(action2string)==null){
                                        continue;
                                    }

                                    else{
                                        // find the item
                                        Items selectedItem = player.findItemByName(action2string); 

                                        System.out.println(String.format("%s: %s. You have %s of it.\n", 
                                                                            selectedItem.getName(),
                                                                            selectedItem.getDescription(),
                                                                            itemNums.get(selectedItem)));   
                                        
                                        // manipulate items
                                        while(true) {
                                        	
                                            // update time in the loop
                                        	cal = Calendar.getInstance();
                                            timeEclipsed=(
                                            			(cal.get(Calendar.MINUTE)-startMinute)*60+
                                            			 cal.get(Calendar.SECOND)-startSecond)/60;
                                            time.setHour((int)timeEclipsed+time.getHours());
                                            player.loseHP((int)(timeEclipsed));
                                            time.isNewDay();
                                            time.remindNewDay();
                                            
                                            System.out.println("\n-------------------Menu-----------------\n"
                                            				 + "use | combine | discard | destroy | quit\n"
                                            				 + "----------------------------------------\n");
                                            
                                            //get user input
                                            List<String> action3 = input.getUserWords(">");
                                            
                                            // if q break
                                            if(action3.contains("quit")) {

                                                System.out.println("\n-------------------Menu-------------------\n"
                                    				 	+ "quit | items | exits | search | pick | HP \n"
                                                    + "------------------------------------------");
                                                break;
                                            }

                                            // if use 
                                            else if(action3.contains("use")) {
                                                
                                                // GameTime time for torch and watch/phone
                                                // Place here for keys
                                                // 1 for default-> use 1 item
                                                gameStatus=player.useItem(selectedItem, time, here, 1, gameStatus);
                                                System.out.println("\n-------------------Menu-------------------\n"
                                    				 	+ "quit | items | exits | search | pick | HP \n"
                                                    + "------------------------------------------");
                                                break;
                                            }
                                            
                                            // if combine
                                            else if(action3.contains("combine")) {
                                                // find a map of items left
                                                Map<Items,Integer> left = itemNums;
//                                                left.put(selectedItem, itemNums.get(selectedItem)-1);
                                                System.out.println("\n-------------------Menu-----------------\n"
                                       				 + " (the item you want to combine it with)\n"
                                       				 + "----------------------------------------\n");
                                                // ask the user what item to combine the current with
                                                List<String> action4 = input.getUserWords(">");
                                                Items item2 = player.findItemByName(action4.get(0).toLowerCase());
                                                
                                                // check if the player has the item
                                                if((left.containsKey(item2) && (left.get(item2)!=0))) {
                                                    player.combineItems(selectedItem, item2);
                                                }else{
                                                    System.out.println("You don't have this item with you.");
                                                }
                                                System.out.println("\n-------------------Menu-------------------\n"
                                    				 	+ "quit | items | exits | search | pick | HP \n"
                                                    + "------------------------------------------");
                                                break;
                                            }

                                            // if discard
                                            else if(action3.contains("discard")){
                                                System.out.println("Enter the amount you wanna discard. Please don't enter things other than numbers. "
                                                		+ "\nOtherwise your world will crash because the demiurge was too busy to check your choice.");
                                                
                                                List<String> action4 = input.getUserWords(">");
                                                int amount=Integer.parseInt(action4.get(0).toLowerCase());
                                                
                                                ArrayList<Items> discardedItems=new ArrayList<Items>();
                                                
                                                // if the player doesn't want to discard all items
                                                if(amount<itemNums.get(selectedItem)) {
                                                    discardedItems = player.discard(selectedItem,amount);
                                                    System.out.println("Done.");
                                                }
                                                // else just discard all
                                                else {
                                                    discardedItems = player.discard(selectedItem);
                                                    System.out.println("You just discarded all of them.");
                                                }
                                                
                                                // add these items to the current place
                                                here.addItem(discardedItems.get(0), discardedItems.size()-1);
                                                
                                                System.out.println("\n-------------------Menu-------------------\n"
                                    				 	+ "quit | items | exits | search | pick | HP \n"
                                                    + "------------------------------------------");
                                                break;
                                            }

                                            // if destroy
                                            else if(action3.contains("destroy")){
                                                
                                                System.out.println("The god didn't have time to write a partially destroy function. "
                                                        + "You destroyed all of them.");
                                                player.destroyItem(selectedItem);
                                              
                                                System.out.println("\n-------------------Menu-------------------\n"
                                    				 	+ "quit | items | exits | search | pick | HP \n"
                                                    + "------------------------------------------");
                                                break;
                                            }
                                            
                                            else {
                                            	System.out.println("No such selection..");
                                            	continue;
                                            }
                                        }
                                     
                                        break;
                                
                                    }
                                                        
                            }
                        }
                    }
                            
                    
                    // if "exits", print out all exit information
                    else if(action.contains("exit")||action.contains("exits")) {
                        
                        // Show a user the ways out of this place.
                        for (Exit e : exits) {
                            
                            // do not print if secret
                            if((e instanceof SecretExit)) {
                                if(((SecretExit)e).isFound()!=false) {
                                    System.out.println(" - " + e.getDescription());             
                                }
                            }
                            else {
                                
                                System.out.println(" - " + e.getDescription());             

                            }
                        }
                        continue;
                    
                    }

                    // if "search", search the current place
                    else if(action.contains("search")) {
                        
                        player.search(here);   
                        // print out all item name in the current place
                        if(placeItems.isEmpty()==false) {
                            System.out.println("You found these items:");
                            for(Items i : placeItems) {
                                System.out.println(String.format("-> %s*%s", i.getName(),here.getItems().get(i)));
                            }
                        }else {
                        	System.out.println("You din't find anything noticeable here.");
                        }
                        continue;
                    }                    
                    
                    // if "pick', pick up items
                    else if(action.contains("pick")) {
                        

                    	System.out.println("\n-------------------Menu-----------------\n"
            					+ " (item name) => pick | quit\n"
       				 			+ "----------------------------------------\n");
                        String userInput = input.getUserWords(">").get(0).toLowerCase();
                        if(userInput.equals("quit")){
                        	System.out.println("\n-------------------Menu-------------------\n"
                				 	+ "quit | items | exits | search | pick | HP \n"
                				 	+ "------------------------------------------");
                        	continue;
                        
                        }
                        else{}
                            // get the item the player wants to pick up
                            Items pickupItem=here.findItemByName(userInput);
                            // pick up 1 item by default
                            boolean success = here.decreaseItem(pickupItem, 1);
                            if (success) {
                                player.pickUpItem(pickupItem, 1);
                            }
                        
                        System.out.println("\n-------------------Menu-------------------\n"
                				+ "quit | items | exits | search | pick | HP \n"
                				+ "------------------------------------------");
                        continue;
                    }

                    // if "HP", check player hp
                    else if(action.contains("hp")){
                    	
                        System.out.println(String.format(("Your current HP is %s"),player.getHP()));
                        continue;
                        
                    }
                
                    // else see if what the user typed matches any exits.
                    else{
                        HashSet<Exit> matches = new HashSet<>();
                        for (Exit e : exits) {
                        	if((e instanceof SecretExit)==false||((SecretExit)e).isFound()==true) {
                            List<String> keywords = WordSplitter.splitTextToWords(e.getDescription());
	                            for (String a : action) {
	                                if (keywords.contains(a)) {
	                                    matches.add(e);
	                                    break;
	                                }
	                            }
                        	}
                        }
                        // If they typed a unique word, they want to go there.
                        if (matches.size() == 1) {
                            
                            Exit e = matches.iterator().next();
                            if(((e instanceof LockedExit)==false)||(((LockedExit)e).isLocked()==false)) {
                            	// action player.enterRoom
	                            place = player.enterRoom(e,deltaHP);
	                            // document the previous place
	                            // Print the description of where you are.
	                            here = game.getPlace(place.toLowerCase());                
	                            // get the description according to time if it's a new place
	            	            if(here.getDescription(time)!=null) {
            	                    System.out.println(here.getDescription(time));
            	                }
            	                else {
            	                    System.out.println("It's an undescriptable place.");
            	                }	            
	            	            
	                            // get all possible exits
	                            exits = here.getExits();
	                            
	                            // print out also the item descriptions in the room.
	                            placeItems = here.showItems();
                 
                            }else {
                            	System.out.println("The exit is locked.");
                            }
	 
                            continue;
                            
                        } 
                        else if (matches.size() >= 2) {
                            // If the input matches more than one term, express our confusion.
                            System.out.println("I can't tell which you mean:");
                            for (Exit e : matches) {
                                System.out.println(" - " + e.getDescription());
                            }
                            
                            continue;
                            
                        } 

                        else {
                            // If there were no matches at all.
                            System.out.println("I'm not sure what you mean by: " + action);
                        }
                        continue;
                    }
                }
            }
        }
            // You get here by "quit" or by reaching a Terminal Place.
            System.out.println(">>> GAME OVER <<<");
        }

}
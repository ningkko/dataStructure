package edu.smith.cs.csc212.p4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

public class Player {

	/**
	 * HP::=Life value
	 */
	private int HP;
	
	/**
	 * name of the player
	 */
	private String name;
	
	/**
	 * items and amounts of items the player has
	 */
	private Map<Items,Integer> items;

	/**
	 * Where the player's going to be when the game isbinitialized
	 */
	private String startPlace;
	
	
	/**
	 * constructor for the Player class
	 * 
	 * @param name
	 * @param HP
	 */
	public Player(String name, int HP, String startPlace) {
		this.setName(name);
		this.HP=HP;
		this.items=new HashMap<Items,Integer>();
		this.startPlace="entranceHall";
		// the player has a watch from the beginning
		items.put(new Watch("watch","This is a watch. It's a little bit broken.","Your watch is lying on a table."),1);
	
	}

	
	
	public String getStartPlace() {
		
		return this.startPlace;
		
	}
	/**
	 * let the player lose HP
	 * 
	 * @param amount - The amount of HP the player's gonna lose.
	 * @return new HP
	 */
	public int loseHP(int amount) {
		return HP-=amount;
	}
	/**
	 * let the player gain HP
	 * 
	 * @param amount - The amount of HP the player's gonna gain.
	 * @return new HP
	 */
	public int gainHP(int amount) {
		return HP+=amount;
	}
	
	
	/**
	 * Action: enter a room
	 * 
	 * @param amount - The cost of HP to have this done..
	 * @return New exit
	 */
	public String enterRoom(Exit e, int amount) {
	
		//it takes libido to have things done.
		this.loseHP(amount);
		
		return e.getTarget();
	}
	
	/**
	 * 
	 * @param item name
	 * @return corresponding item
	 */
	public Items findItemByName(String name) {
		
		/**
		 * items the player has
		 */
		
		Set<Items> items = this.items.keySet();

		// loop thru to check name
		for(Items i:items) {
			if(i.getName().toLowerCase().equals(name.toLowerCase())) {
				return i;
			}
		}
		System.out.println("No items with such name found.");
		return null;
		
	}
	
	/**
	 * Action: pick up items
	 * 
	 * @param item 
	 * @param amount
	 */
	public void pickUpItem(Items item, Integer amount) {
		
		this.items.put(item, amount);
		System.out.println(String.format("You picked up \"%S\" *%S",item.getName(),amount));
		
	}
	
	
	/**
	 * Action: discard some items
	 * 
	 * @param item
	 * @param amount
	 * 	 the amount of items the player wants to discard.
	 * @return a list of all such items the player has.
	 */
	public ArrayList<Items> discard(Items item, Integer amount){
		
		// subtract items
		this.items.put(item, this.items.get(item)-amount);
		
		ArrayList<Items> discarded= new ArrayList<Items>();
		for(int i=0;i<=amount;i++) {
			discarded.add(item);
		}
		
		return discarded;
		
	}	
	
	/**
	 * @override 
	 * 		discard all
	 */
	public ArrayList<Items> discard(Items item){
		
		//get the amount of the item
		int amount=this.items.get(item);
		//convert to list for return
		ArrayList<Items> discarded= new ArrayList<Items>();
		for(int i=0;i<=amount;i++) {
			discarded.add(item);
		}
		
		this.items.remove(item);
		
		return discarded;
		
	}	
	
	/**
	 * Action: Use an item
	 * 
	 * @param item
	 * @return gameStatus
	 */
	public boolean useItem(Items item,GameTime time, Place place,Integer amount,boolean gameStatus) {
		
		for(int i=0;i<amount;i++) {
			
		
			if(this.items.get(item)>0) {
	
				// get the item name.
				String itemName = item.getName().toLowerCase();
	
				if(item instanceof Food) {
					
					if(((Food) item).isPoisonous()==false) {
						// if not poisonous add HP.
						System.out.println(String.format("You eat the %s, you feel refreshed.",itemName));
						this.gainHP(10);
					}else {
						// decrease HP.
						System.out.println(String.format("You eat the %s, you don't feel good...",itemName));
						this.loseHP(20);
					}
					// decrease the item by one
					if(this.items.get(item)-1!=0) {
						this.items.put(item, this.items.get(item)-1);
					}
					else {
						this.items.remove(item);
					}
					
				}
	
				else if(item instanceof Torch) {
					
					// Can't use torch during daytime.
					if(time.isNight()==false) {
						System.out.println("It's daytime, you don't need a torch.");
					}

					else {
						((Torch) item).setTurnedOn(true);
						System.out.println("You turned on the torch.");
					}
					if(place.getId().toLowerCase().equals("wardroberoom")&&time.isNight()&& ((Torch) item).isTurnedOn()) {
						System.out.println("With the torch, you found that in the thick pile of clothes there was a broken mirror.\n"
								+ "You looked into the mirror when picking it up."
								+ "But you saw nothing in the mirror except those bloody fish hanging behind you on the wall..\n"
								+ "...\n"
								+ "You feel released after all.");
						return false;
					}
					
					
				}
			
				
				else if(item instanceof Phone){
					
					if(((Phone) item).hasPower()){
						System.out.println(String.format("You tried to use the phone, but there's no signal. "
								+ "The time is %s o'colck.",time.getHours()));
					}else {
						System.out.println("The phone doesn't have a battery.. You enjoyed its Rococo design.");
					}
				}
				
				else if(item instanceof Watch) {
					
					System.out.println(String.format("You looked at the watch, it says it's %s o'clock. "
							+ "But you don't know if it's correct",time.getHours()));
				}
				
				else if(item instanceof Key){
					
					boolean hasDoor=false;
					for(Exit e:place.getExits()) {
						if(e instanceof LockedExit) {
							this.openDoorWithKey((Key)item, (LockedExit)e);
							hasDoor=true;
						}
					}
					
					if(hasDoor==false) {
						System.out.println("You can't use it here... Find a lock first.");
					}
					
				}
				
				else if(item instanceof UselessItems) {
					System.out.println(String.format("You used the %s, but nothing happened",item.getName()));
					// decrease the item by one
					if(this.items.get(item)-1!=0) {
						this.items.put(item, this.items.get(item)-1);
					}
					else {
						this.items.remove(item);
					}
				}
				
			}else {
			
				System.out.println("You don't currently have this item.");
			}
		}
		return true;
	}
	
	/**
	 * Action: check what items the player currently has
	 * 
	 * prints out the name of items 
	 * 
	 * @return if there are items
	 */
	public boolean checkItems() {
		
		//get the items.
		Set<Entry<Items, Integer>> items=this.items.entrySet();
		
		//if empty
		if(items.isEmpty()) {
			
			System.out.println("You don't have anything.");
			return false;
		
		}else {
			//print items the player currently has.
			
			System.out.println("You currently have: ");
			
			for(Map.Entry<Items,Integer> entry:items) {
			
				// print out the name of each item
				System.out.println("-> "+entry.getKey().getName());
				
			}
			
			return true;
		}
	}
	
	/**
	 * Action, the player checks the time on the watch
	 * 
	 * Prints the current time.
	 */
	public void checkWatch() {
		
		int time = 0;
				
		//get the watch from the list 
		for (Items item:this.items.keySet()) {
			if(item.getName() == "watch") {
				
				//get time
				time=((Watch)item).getTime();	
			}
		}
		
		System.out.println(String.format("You looked at your watch. It's %s o'clock now.",time));
		
	}
	
	/**
	 * Action; open a locked exit
	 * 
	 * @param key
	 * @param lockedExit
	 */
	public void openDoorWithKey(Key key, LockedExit lockedExit) {

		// if right key used
		if(lockedExit.getKey().equals(key.getName())){
			//open it
			lockedExit.setLocked(false);
			System.out.println("You unlocked the door.");
		}else {
			System.out.println("This is not the right Key");
		}
	}
	
	/**
	 * Action; lock a lockable exit
	 * 
	 * @param key
	 * @param lockedExit
	 */
	public void lockDoorWithKey(Key key, LockedExit lockedExit) {

		// if right key used
		if(lockedExit.getKey().equals(key.getName())){
			//lock it
			lockedExit.setLocked(true);
		}else {
			System.out.println("This is not the right Key");
		}
	}
	
	/**
	 * Action::= search
	 * 		to search if there are hidden exits in the room
	 * 
	 * @param place
	 * 		the current place the player is in
	 * @return boolean
	 */
	public boolean search(Place place){

		for(Exit current:place.getExits()) {
			if(current instanceof SecretExit) {

				//set the isFound of the secret exit to true
				((SecretExit) current).setFound(true);
				System.out.println("You found a new exit."
						+ String.format("\n-> %s", current.getDescription()));

				return true;
			}
		}
	
		System.out.println("Seems like there are no more rooms to be found.");
		return false;
	}
	
	/**
	 * Action::= Destroy an item
	 * 
	 * @param item
	 * @return successful or not
	 */
	public boolean destroyItem(Items item){
		
		if(this.items.containsKey(item)){
			this.items.put(item,0);
			return true;
		}		
		System.out.println("Oops.. You should never see this line...\nSomething went wrong..");
		return false;
		
	}	
	
	/**
	 * Action::= combine two items together
	 * 
	 * @param item1
	 * @param item2
	 * @return boolean successful
	 */
	public boolean combineItems(Items item1, Items item2) {
				
		
		// if phone and battery
		
		if((item1 instanceof Phone)&&(item2 instanceof Battery)){
			
			// battery gone
			this.items.remove(item2);
			// turn on the phone
			((Phone)item1).setHasPower(true);
			
			System.out.println("You put the battery into the phone. The phone seems working now...");
			return true;
			
		}
		else if((item1 instanceof Battery)&&(item2 instanceof Phone)){
			
			// battery gone
			this.items.remove(item1);
			// turn on the phone
			((Phone)item2).setHasPower(true);
			System.out.println("You put the battery into the phone. The phone seems working now...");
			return true;
		}
		
		
		
		// if food and food
		else if((item2 instanceof Food)&&(item1 instanceof Food)){
			
			// there's chance to make the food poisonous.
			Random rand=new Random();
			if(rand.nextInt(2)==1) {
				((Food)item1).setPoisonous(true);
			}
			
			item1.setDescription(String.format("Out of the nature of exploring our great cosmos, you put %s and %s together. This new food looks poisonous, but you don't feel sense of fear.",item1.getName(),item2.getName()));
			this.items.put(item2,this.items.get(item2)-1);
			// turn on the phone
			item1.setName("A new kind of weird food");
			return true;
			
		}
		
		// if some nonsense things
		else if((item1 instanceof UselessItems)&&(item2 instanceof UselessItems)){
			
			item1.setDescription(String.format("You somehow combined %s and %s, a new piece of art is created.",item1.getName(),item2.getName()));
			this.items.put(item2,this.items.get(item2)-1);
			// turn on the phone
			item1.setName(String.format("A new form of %s",item1.getName()));
			return true;
			
		}

		
		else {
			System.out.println("The demiurge wonders how you can combine these things together based on current physics laws..");
		}
								
	
		return false;
		
	}
	
	/**
	 * getter function for player.HP
	 * 
	 * @return player.HP
	 */
	public int getHP() {
		
		return this.HP;
		
	}
	
	/**
	 * setter function for player.HP
	 * @param player.newHP
	 */
	public void setHP(int newHP){
		
		this.HP=newHP;
		
	}
	
	/**
	 * getter function for name
	 * 
	 * @return player.name
	 */
	public String getName() {
		return name;
	}

	/**
	 * setter function for name
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * getter for items
	 * 
	 * @return items
	 */
	public Map<Items,Integer> getItems(){
		return this.items;
	}

}

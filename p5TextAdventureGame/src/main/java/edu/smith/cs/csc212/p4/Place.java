package edu.smith.cs.csc212.p4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

/**
 * This represents a place in our text adventure.
 * @author jfoley
 *
 */
public class Place {
	/**
	 * This is a list of places we can get to from this place.
	 */
	private List<Exit> exits;
	/**
	 * This is the identifier of the place.
	 */
	private String id;
	/**
	 * What to tell the user about this place.
	 * Day version
	 */
	private String dayDescription;
	
	/**
	 * What to tell the user about this place.
	 * Night version
	 */
	private String nightDescription;
	
	/**
	 * Whether reaching this place ends the game.
	 */
	private boolean terminal;
	
	/**
	 * items and amounts of items the place has
	 */
	private Map<Items,Integer> items;

	
	/**
	 * Internal only constructor for Place. Use {@link #create(String, String)} or {@link #terminal(String, String)} instead.
	 * @param id - the internal id of this place.
	 * @param description - the user-facing description of the place.
	 * @param terminal - whether this place ends the game.
	 */
	public Place(String id, String dayDescription, String nightDescription, boolean terminal) {
		this.id = id;
		this.dayDescription = dayDescription;
		this.nightDescription=nightDescription;
		this.exits = new ArrayList<>();
		this.terminal = terminal;
		this.items=new HashMap<Items,Integer>();
	}
	

	/**
	 * Create an exit for the user to navigate to another Place.
	 * @param exit - the description and target of the other Place.
	 */
	public void addExit(Exit exit) {
		this.exits.add(exit);
	}
	
	/**
	 * For gameplay, whether this place ends the game.
	 * @return true if this is the end.
	 */
	public boolean isTerminalState() {
		return this.terminal;
	}
	
	/**
	 * The internal id of this place, for referring to it in {@link Exit} objects.
	 * @return the id.
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * The narrative description of this place.
	 * @param GameTime time
	 * 		the current time
	 * @return what we show to a player about this place.
	 */
	public String getDescription(GameTime time) {
		
		if(time.isNight()) {
		
			return this.dayDescription;
		}
		
		return this.nightDescription;
		
	}

	/**
	 * Get a view of the exits from this Place, for navigation.
	 * @return all the exits from this place.
	 */
	public List<Exit> getExits() {
		return Collections.unmodifiableList(exits);
	}
	
	/**
	 * This is a terminal location (good or bad).
	 * @param id - this is the id of the place (for creating {@link Exit} objects that go here).
	 * @param description - this is the description of the place.
	 * @return the Place object.
	 */
	public static Place terminal(String id, String dayDescription, String nightDescription) {
		return new Place(id, dayDescription, nightDescription, true);
	}
	
	/**
	 * @override
	 * 
	 */
	public static Place terminal(String id, String description) {
		return new Place(id, description, description, true);
	}
	
	/**
	 * Create a place with an id and description.
	 * @param id - this is the id of the place (for creating {@link Exit} objects that go here).
	 * @param description - this is what we show to the user.
	 * @return the new Place object (add exits to it).
	 */
	public static Place create(String id, String dayDescription, String nightDescription) {
		return new Place(id, dayDescription, nightDescription,false);
	}
	
	/**
	 * @override
	 */
	public static Place create(String id, String description) {
		
		return new Place(id, description, description,false);
	}
	
	/**
	 * Implements what we need to put Place in a HashSet or HashMap.
	 */
	public int hashCode() {
		return this.id.hashCode();
	}
	
	/**
	 * Give a string for debugging what place is what.
	 */
	public String toString() {
		return "Place("+this.id+" with "+this.exits.size()+" exits.)";
	}
	
	/**
	 * Whether this is the same place as another.
	 */
	public boolean equals(Object other) {
		if (other instanceof Place) {
			return this.id.equals(((Place) other).id);
		}
		return false;
	}
	
	/**
	 * find the corresponding item with a given name
	 * 
	 * @param String item.name
	 * @return Item item
	 */
	public Items findItemByName(String name) {
		
		/**
		 * items the place has
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
	 * To add items to the place
	 * 
	 * @param item
	 * @param amount
	 */
	public void addItem(Items item,int amount) {
		
		// if there's already such item in the place, update amount
		if(this.items.containsKey(item)) {
			this.items.put(item, this.items.get(item)+amount);
		}
		
		// else update items
		else {
			this.items.put(item, amount);
		}
	}
	
	/**
	 * To decrease items in the place
	 * 
	 * @param item
	 * @param amount
	 * @return if successfully decreased
	 */
	public boolean decreaseItem(Items item,int amount) {

		if(this.items.containsKey(item)) {
			
			if(this.items.get(item)!=null) {
				
				if(this.items.get(item)!=amount) {
					this.items.put(item, this.items.get(item)-amount);
					return true;
				}
				
				else {
					this.items.remove(item);
					return true;
				}
			}
			
			// else update items
			else {
				return false;
			}
		}
		return false;
	}
	/**
	 * prints out all items in the place and return a list of items.
	 */
	public ArrayList<Items> showItems() {
		//get the items.
		
		ArrayList<Items> items=new ArrayList<Items>();
		//if empty
		if(this.items.isEmpty()==false) {
			//print items the player currently has.
			
			
			for(Items i:this.items.keySet()) {
			
				items.add(i);
				// print out the name of each item
				if(this.items.get(i)!=0){
					System.out.println("-> "+i.getPlaceDescription());
				}
			}
		}
		return items;
	}

	
	/**
	 * getter for place.items
	 * 
	 * @return place.items
	 */
	public Map<Items, Integer> getItems() {
		return this.items;
	}
	
	
	/**
	 * getter for night description
	 * 
	 * @return nightDescription
	 */
	public String getNightDescription() {
		return nightDescription;
	}

	/**
	 * Setter for night description
	 * 
	 * @param nightDescription
	 */
	public void setNightDescription(String nightDescription) {
		this.nightDescription = nightDescription;
	}
	
}
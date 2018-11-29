package edu.smith.cs.csc212.p4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
	 */
	private String description;
	/**
	 * Whether reaching this place ends the game.
	 */
	private boolean terminal;
	
	/**
	 * Whether the room is hidden.
	 */
	private boolean hidden;
	
	/**
	 * Whether the room needs a key to enter.
	 */
	private boolean locked;
	
	/**
	 * Whether the room only appear for once.
	 */
	private boolean ghostRoom;
	
	/**
	 * Whether the room collapsed.
	 */
	private boolean collapsed;
	
	/**
	 * Internal only constructor for Place. Use {@link #create(String, String)} or {@link #terminal(String, String)} instead.
	 * @param id - the internal id of this place.
	 * @param description - the user-facing description of the place.
	 * @param terminal - whether this place ends the game.
	 */
	private Place(String id, String description, boolean terminal, boolean hidden, boolean locked, boolean ghostRoom) {
		this.id = id;
		this.description = description;
		this.exits = new ArrayList<>();
		this.terminal = terminal;
		this.ghostRoom=ghostRoom;
		this.locked=locked;
		this.hidden=hidden;
		this.collapsed=false;
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
	 * For gameplay, whether this room is locked.
	 * @return true if this is locked.
	 */
	public boolean isLocked() {
		return this.locked;
	}
	
	/**
	 * For gameplay, whether this room is hidden.
	 * @return true if this is hidden.
	 */
	public boolean isHidden() {
		return this.hidden;
	}
	
	/**
	 * For gameplay, whether this room will collapse.
	 * @return true if this r.
	 */
	public boolean ghostRoom() {
		return this.ghostRoom;
	}
	/**
	 * For gameplay, whether this place collapsed.
	 * @return true if this is collapsed.
	 */
	public boolean collapsed() {
		return this.collapsed;
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
	 * @return what we show to a player about this place.
	 */
	public String getDescription() {
		return this.description;
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
	public static Place createTerminal(String id, String description) {
		return new Place(id, description, true, false, false, false);
	}
	
	/**
	 * Create a place with an id and description.
	 * @param id - this is the id of the place (for creating {@link Exit} objects that go here).
	 * @param description - this is what we show to the user.
	 * @return the new Place object (add exits to it).
	 */
	public static Place createNormal(String id, String description) {
		return new Place(id, description, false, false, false, false);
	}
	
	/**
	 * Create a place with an id and description.
	 * @param id - this is the id of the place (for creating {@link Exit} objects that go here).
	 * @param description - this is what we show to the user.
	 * @return the new Place object (add exits to it).
	 */
	public static Place createHidden(String id, String description) {
		return new Place(id, description, false, true, false, false);
	}
	
	/**
	 * Create a place with an id and description.
	 * @param id - this is the id of the place (for creating {@link Exit} objects that go here).
	 * @param description - this is what we show to the user.
	 * @return the new Place object (add exits to it).
	 */
	public static Place createKeyRoom(String id, String description) {
		return new Place(id, description, false, false, true, false);
	}
	
	/**
	 * Create a place with an id and description.
	 * @param id - this is the id of the place (for creating {@link Exit} objects that go here).
	 * @param description - this is what we show to the user.
	 * @return the new Place object (add exits to it).
	 */
	public static Place createGhostRoom(String id, String description) {
		return new Place(id, description, false, false, false, true);
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
	
}

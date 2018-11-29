package edu.smith.cs.csc212.p4;

public class LockedExit extends Exit{

	/**
	 * The corresponding key to the exit
	 */
	private String key;
	
	/**
	 * The locked status of the exit
	 */
	private boolean locked;
	
	
	
	public LockedExit(String target, String description, String key) {
		
		super(target, description);
		
		// the exit is locked at the beginning
		this.locked=true;
		this.key=key;
	}
	
	

	/**
	 * getter for status locked
	 * 
	 * @return locked
	 * 		if the exit is locked
	 */
	public boolean isLocked() {
		return locked;
	}

	/**
	 * setter for status locked
	 * 
	 * @param new status
	 */
	public void setLocked(boolean newStatus) {
		this.locked = newStatus;
	}


	/**
	 * getter for key
	 * 
	 * @return the right key to open the door
	 */
	public String getKey() {
		
		return this.key;
		
	}

	
	
}

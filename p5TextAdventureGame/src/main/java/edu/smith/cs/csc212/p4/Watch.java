package edu.smith.cs.csc212.p4;


public class Watch extends Items{
	
	/**
	 * time displayed by the watch.
	 * Walks slower than the real time.
	 */
	int time;
	

	public Watch(String name, String description,String placeDescription) {
		super(name, description, placeDescription);
	}
	
	/**
	 * getter for watch.time
	 * 
	 * @return watch.time
	 */
	public int getTime() {
		
		return this.time;
		
	}
	
	/**
	 * When the player gets the phone he can reset the time.
	 * 
	 * @param phoneTime
	 */
	public void setTime(int phoneTime) {
		
		this.time=phoneTime;
		
	}
}

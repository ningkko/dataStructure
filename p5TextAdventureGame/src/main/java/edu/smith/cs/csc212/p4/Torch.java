package edu.smith.cs.csc212.p4;

public class Torch extends Items{
	
	/**
	 * if the torch is on
	 */
	private boolean turnedOn;
	
	public Torch(String name, String description, String placeDescription) {
		super(name, description, placeDescription);
	}

	/**
	 * getter for isOn
	 * @return
	 */
	public boolean isTurnedOn() {
		return turnedOn;
	}

	/**
	 * Setter for isOn
	 * @param turnedOn
	 */
	public void setTurnedOn(boolean turnedOn) {
		this.turnedOn = turnedOn;
	}

}

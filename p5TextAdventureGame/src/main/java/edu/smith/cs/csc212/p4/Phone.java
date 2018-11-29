package edu.smith.cs.csc212.p4;


public class Phone extends Items {
	
	/**
	 * time on the phone
	 */
	private Integer phoneTime;
	
	/**
	 * if the player can use the phone
	 */
	private boolean hasPower;

	public Phone(String name, String description, String placeDescription) {
		super(name, description, placeDescription);
		this.hasPower=false;
	}

	/**
	 * getter for phoneTime
	 * @return phoneTime
	 * 
	 */
	public Integer getPhoneTime() {
		if(this.phoneTime!=null) {
			System.out.println("The phone does not have power...");
			return null;
		}
		return phoneTime;
	}
	
	/**
	 * setter for phoneTime
	 * 
	 * @param phoneTime
	 */
	public void setPhoneTime(int phoneTime) {
		
		this.phoneTime = phoneTime;
	}

	/**
	 * getter for has 
	 * 
	 * @return hasPower
	 */
	public boolean hasPower() {
		return hasPower;
	}

	/**
	 * setter for hasPower
	 * @param hasPower
	 */
	public void setHasPower(boolean hasPower) {
		this.hasPower = hasPower;	
	}
	

}

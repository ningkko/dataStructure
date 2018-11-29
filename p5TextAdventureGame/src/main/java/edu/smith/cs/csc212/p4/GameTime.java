package edu.smith.cs.csc212.p4;

public class GameTime {
	
	/**
	 * time in the game
	 */
	
	private int hours;
	
	/**
	 * how many days passed
	 */
	private int days;
	
	public GameTime() {
		// the first day.
		this.days=1;
		//start from 18:00
		this.hours=18;
	}
	
	/**
	 * Checks if it's a new day and set the time to be within [0,23]	 * 
	 * @return if it's 00:00
	 */
	public boolean isNewDay(){
		
		if (this.hours>=24){
			this.days+=(int)(hours/24);
			this.hours=hours%24;
			return true;
		}	
		return false;
		
	}
	
	/**
	 * Prints the number of days past when it's 00:00
	 */
	public void remindNewDay(){
		
		if(this.isNewDay()) {
			
			System.out.println(String.format("It's 00:00. %s days has past.",this.days));
		
		}
	}
	
	/**
	 * Checks if it's night time
	 * 
	 * @return if it's night
	 */
	public boolean isNight() {
		
		if (this.hours>=20||this.hours<=4){
			return true;
		}
		return false;
	}
	
	/**
	 * getter for hours
	 * 
	 * @return int hours
	 */
	public int getHours() {
		
		return this.hours;
		
	}
	
	/**
	 * setter for hours
	 * @param hours
	 */
	public void setHour(int hours) {
	
		this.hours=hours;
	}
	
	/**
	 * getter for days
	 * 
	 * @return int days
	 */
	public int getDays() {
		return this.days;
	}
	
}

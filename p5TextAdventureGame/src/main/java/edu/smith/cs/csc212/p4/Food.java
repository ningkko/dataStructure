package edu.smith.cs.csc212.p4;


public class Food extends Items{
	
	/**
	 * if using the food increases or decreases HP
	 */
	private boolean poisonous;
	
	/**
	 * constructor for Food
	 * @param name
	 * @param description
	 * @param placeDescription
	 * @param combinativeItems
	 */
	public Food(String name, String description, String placeDescription,boolean poisonous) {
		super(name, description, placeDescription);
		this.poisonous=poisonous;
		
	}
	
	/**
	 * @override
	 * @param name
	 * @param description
	 * @param placeDescription
	 */
	public Food(String name, String description, String placeDescription) {
		super(name, description, placeDescription);
		this.poisonous=false;
		
	}
	/**
	 * getter for poisonous
	 * @return
	 */
	public boolean isPoisonous() {
		return poisonous;
	}
	
	/**
	 * setter for poisonous
	 * @param poisonous
	 */
	public void setPoisonous(boolean poisonous) {
		this.poisonous = poisonous;
	}

}

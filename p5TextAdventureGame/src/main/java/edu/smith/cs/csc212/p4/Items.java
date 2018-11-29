package edu.smith.cs.csc212.p4;

import java.util.ArrayList;

public class Items {

	/*
	 * name of the Item
	 */
	private String name;
	
	/*
	 * description of the item
	 */
	private String description;
	
	/**
	 * A description for the item at where it is
	 */
	private String placeDescription;
	
	/**
	 * This tells what the item can be combined with
	 */
	protected ArrayList<String> combinativeItems;
	
	
	
	public Items(String name, String description, String placeDescription) {
		
		this.name=name;
		this.description=description;
		this.placeDescription=placeDescription;
	}

	
	/**
	 * getter for item.name
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * setter for item.name
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * getter for item.description
	 * 
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * setter for item.description
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * getter for PlaceDescription
	 * 
	 * @return placeDescription
	 */
	public String getPlaceDescription() {
		
		return placeDescription;
	}


}

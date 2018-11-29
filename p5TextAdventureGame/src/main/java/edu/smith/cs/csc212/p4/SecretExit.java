package edu.smith.cs.csc212.p4;

public class SecretExit extends Exit{

	/**
	 * it's corresponding key numbet
	 */
	private Integer lockNumber;
	
	/**
	 * if it's found
	 */
	private boolean found;
	
	public SecretExit(String target, String description) {
		super(target, description);
		// TODO Auto-generated constructor stub
	}

	/**
	 * getter for isFound
	 * 
	 * @return found
	 */
	public boolean isFound() {
		return found;
	}

	/**
	 * Setter for found
	 * @param found
	 */
	public void setFound(boolean found) {
		this.found = found;
	}

	/**
	 * getter
	 * 
	 * @return lockNumber
	 */
	public Integer getLockNumber() {
		return lockNumber;
	}



}

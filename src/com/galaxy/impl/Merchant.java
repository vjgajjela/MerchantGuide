package com.galaxy.impl;

/**
 * Interface for Merchant guide application.
 */
public interface Merchant {

	/**
	 * This method internally must executes all steps in the merchant guide
	 * application 1) reading input file, and preparing input 2) processing
	 * statements 3) creating value pairs for units and credits 4) processing
	 * queries 5) preparing output.
	 */
	public void execute();

	/**
	 * @return String
	 * 
	 *         This method should return the input provided by user.
	 */
	public String getInput();

	/**
	 * @return String
	 * 
	 *         This method should return the output result prepared by
	 *         application.
	 */
	public String getOutput();

}

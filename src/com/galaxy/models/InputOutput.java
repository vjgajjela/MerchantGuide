package com.galaxy.models;

import com.galaxy.constants.IConstants;

/**
 * Model holds input provided by user and output/results prepared by
 * application.
 */
public class InputOutput {

	/** Input text. */
	private StringBuilder inputText;

	/** Result/messages. */
	private StringBuilder result;

	/**
	 * Constructor.
	 */
	public InputOutput() {
		this.inputText = new StringBuilder();
		this.inputText.append(IConstants.INPUT);
		this.result = new StringBuilder();
		this.result.append(IConstants.OUTPUT);
	}

	/**
	 * @return StringBuilder
	 * 
	 *         This methods returns input text.
	 */
	public StringBuilder getInputText() {
		return this.inputText;
	}

	/**
	 * @param inputText
	 * 
	 *            This methods sets input text.
	 */
	public void setInputText(final StringBuilder inputText) {
		this.inputText = inputText;
	}

	/**
	 * @return
	 * 
	 *         This methods returns output/result text.
	 */
	public StringBuilder getResult() {
		return this.result;
	}

	/**
	 * @param result
	 * 
	 *            This methods sets output/result text.
	 */
	public void setResult(final StringBuilder result) {
		this.result = result;
	}

}

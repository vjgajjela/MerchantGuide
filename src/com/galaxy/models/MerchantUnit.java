package com.galaxy.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.galaxy.constants.IConstants;
import com.galaxy.exception.InvalidRomanException;
import com.galaxy.exception.InvalidStatementException;
import com.galaxy.utils.MerchantUtils;

/**
 * This class hold Unit conversion statements and process these to create unit.
 * value map.
 */
public class MerchantUnit {

	/** unit conversion statements. */
	private final List<String> unitStmnts;

	/** unit values mapping. */
	private final Map<String, String> unitValueMap;

	/** Constructor. */
	public MerchantUnit() {
		this.unitStmnts = new ArrayList<String>();
		this.unitValueMap = new HashMap<String, String>();
	}

	/**
	 * @return List<String>
	 * 
	 *         This method returns unit conversion statement list.
	 */
	public List<String> getUnitStmnts() {
		return this.unitStmnts;
	}

	/**
	 * @return Map<String, String>
	 * 
	 *         This method returns unit value map.
	 */
	public Map<String, String> getUnitValueMap() {
		return this.unitValueMap;
	}

	/**
	 * @throws InvalidRomanException
	 * @throws InvalidStatementException
	 * 
	 *             This method processes unit conversion statements and created
	 *             unit value map.
	 */
	public void createUnitValueMap() throws InvalidRomanException, InvalidStatementException {
		if (this.unitStmnts.isEmpty()) {
			// no units provided
			throw new InvalidStatementException(IConstants.MSG_1);
		}

		// error message
		final StringBuilder message = new StringBuilder();

		// processing unit conversion statements
		for (final String unitStmnt : this.unitStmnts) {
			final String[] unitValueArr = unitStmnt.split(IConstants.IS);
			if (unitValueArr.length > 1 && MerchantUtils.isValidRoman(unitValueArr[1].trim())) {
				this.unitValueMap.put(unitValueArr[0].trim(), unitValueArr[1].trim());
			} else {
				// adding message for invalid conversion statements
				message.append(IConstants.MSG_2).append(unitStmnt).append(IConstants.NEW_LINE);
			}
		}

		if (message.length() > 0) {
			throw new InvalidRomanException(message.toString());
		}
	}

}

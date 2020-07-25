package com.galaxy.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.galaxy.constant.IConstants;
import com.galaxy.exception.InvalidRomanException;
import com.galaxy.exception.InvalidStatementException;
import com.galaxy.util.MerchantUtils;

/**
 * Model holds credit statements and subject credit mappings.
 */
public class MerchantCredits {

	/** Credit statements list. */
	private List<String> creditStmnts;

	/** Subject credit mappings. */
	private Map<String, Double> subjectCreditMap;

	/** Constructor. */
	public MerchantCredits() {
		this.creditStmnts = new ArrayList<String>();
		this.subjectCreditMap = new HashMap<String, Double>();
	}

	/**
	 * @return Map<String, Double>
	 *
	 *         This method returns subject credit map.
	 */
	public Map<String, Double> getSubjectCreditMap() {
		return this.subjectCreditMap;
	}

	/**
	 * @param subjectCreditMap
	 *
	 *            This method sets subject credit map.
	 */
	public void setSubjectCreditMap(final Map<String, Double> subjectCreditMap) {
		this.subjectCreditMap = subjectCreditMap;
	}

	/**
	 * @return
	 *
	 * 		This method returns credit statements list.
	 */
	public List<String> getCreditStmnts() {
		return this.creditStmnts;
	}

	/**
	 * @param creditStmnts
	 *
	 *            This method sets credit statement list.
	 */
	public void setCreditStmnts(final List<String> creditStmnts) {
		this.creditStmnts = creditStmnts;
	}

	/**
	 * @param unitMap
	 * @throws InvalidStatementException
	 * @throws InvalidRomanException
	 *
	 *             This method creates subject credit map by processing the
	 *             credit statements and using unit value map.
	 */
	public void createSubjectCreditMap(final Map<String, String> unitMap)
			throws InvalidStatementException, InvalidRomanException {

		// processing credit statements.
		for (final String creditStmnt : this.creditStmnts) {
			// error message
			final StringBuilder message = new StringBuilder();

			final String[] creditArr = creditStmnt.split(IConstants.IS);
			if (creditArr.length > 1) {
				final String[] unitAndSubjArr = creditArr[0].split(IConstants.SPACE);
				String subject = null;
				String unitRomanVal = "";
				int i = 1;
				for (final String unitOrSubject : unitAndSubjArr) {
					if (unitMap.containsKey(unitOrSubject.trim())) {
						unitRomanVal = unitRomanVal + unitMap.get(unitOrSubject.trim());
					} else if (i == unitAndSubjArr.length) {
						subject = unitOrSubject.trim();
					} else {
						// collecting invalid units or subjects.
						message.append(unitOrSubject).append(IConstants.SPACE);
					}
					i++;
				}

				// throwing invalid statement exception with statement
				if (message.length() > 0) {
					message.insert(0, IConstants.MSG_5_1).append(creditStmnt).append(IConstants.MSG_5_2)
							.append(IConstants.NEW_LINE);
					throw new InvalidStatementException(message.toString());
				}

				// throwing invalid Roman exception with statement
				if (!MerchantUtils.isValidRoman(unitRomanVal)) {
					throw new InvalidRomanException(IConstants.MSG_6 + creditArr[0]);
				}

				// extracting credit number from the statement.
				final String credits = creditArr[1].replaceAll(IConstants.REGEX_NUM_ONLY, IConstants.BLANK);

				if (credits.isEmpty()) {
					// throwing invalid statement exception with statement if no
					// credits provided.
					throw new InvalidStatementException(IConstants.MSG_7 + creditStmnt);
				}

				// putting subject and its credit value into map after
				// processing
				this.subjectCreditMap.put(subject,
						Double.parseDouble(credits) / MerchantUtils.romanToNumber(unitRomanVal));
			} else {
				// if credit array length is 1 or less then adding invalid
				// credit message.
				message.append(IConstants.MSG_8).append(creditStmnt);
			}
			// System.out.println(this.subjectCreditMap);
		}

	}
}

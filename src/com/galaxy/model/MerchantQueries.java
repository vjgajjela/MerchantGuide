package com.galaxy.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.galaxy.constant.IConstants;
import com.galaxy.util.MerchantUtils;

/**
 * Class holds queries list provided by the user and processes these queries and
 * prepares the output.
 */
public class MerchantQueries {

	/** Query statements list */
	private List<String> queryStmnts;

	/**
	 * Constructor.
	 */
	public MerchantQueries() {
		this.queryStmnts = new ArrayList<String>();
	}

	/**
	 * @return List<String>
	 *
	 *         This method returns query statements list.
	 */
	public List<String> getQueryStmnts() {
		return this.queryStmnts;
	}

	/**
	 * @param queryStmnts
	 *
	 *            This method sets query statements list.
	 */
	public void setQueryStmnts(final List<String> queryStmnts) {
		this.queryStmnts = queryStmnts;
	}

	/**
	 * @param unitsMap
	 * @param creditsMap
	 * @return String
	 *
	 *         This method compiles queries in queries list with the conversion
	 *         mapping in unit-value map and subject credit map , and prepares
	 *         output.
	 */
	public String compileQueries(final Map<String, String> unitsMap, final Map<String, Double> creditsMap) {
		// result
		final StringBuffer result = new StringBuffer();

		// processing query statements.
		for (final String query : this.queryStmnts) {
			String subject = null;
			String unitRomanVal = "";
			final String[] queryArr = query.split(IConstants.IS);

			if (queryArr.length != 2) {
				// invalid query message
				result.append(IConstants.MSG_9).append(IConstants.NEW_LINE);
				continue;
			}
			// unit and/or subject list
			final String unitsOrSubList = queryArr[1].replaceAll(IConstants.REGEX_QN, IConstants.BLANK);

			// processing unit and/or subject array
			final String[] unitsOrSubArr = unitsOrSubList.split(IConstants.SPACE);
			for (String unitOrSubject : unitsOrSubArr) {
				unitOrSubject = unitOrSubject.trim();
				if (unitsMap.containsKey(unitOrSubject)) {
					unitRomanVal = unitRomanVal + unitsMap.get(unitOrSubject);
				} else {
					subject = unitOrSubject;
				}
			}

			// adding error message if the Roman is invalid
			if (!MerchantUtils.isValidRoman(unitRomanVal)) {
				result.append(IConstants.MSG_6).append(query).append(IConstants.NEW_LINE);
				continue;
			}

			if (null == subject) {
				// query with units only
				result.append(unitsOrSubList).append(IConstants.IS).append(MerchantUtils.romanToNumber(unitRomanVal))
						.append(IConstants.NEW_LINE);

			} else if (creditsMap.containsKey(subject)) {
				// query with units and subject
				result.append(unitsOrSubList).append(IConstants.IS)
						.append((long) (MerchantUtils.romanToNumber(unitRomanVal) * creditsMap.get(subject)))
						.append(IConstants.CREDITS).append(IConstants.NEW_LINE);

			} else {
				// invalid query
				result.append(IConstants.MSG_9).append(IConstants.NEW_LINE);
			}

		}
		return result.toString();
	}

}

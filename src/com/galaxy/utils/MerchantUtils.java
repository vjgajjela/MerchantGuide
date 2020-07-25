package com.galaxy.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.galaxy.constants.IConstants;

/**
 * Class for Merchant utilities methods.
 */
public class MerchantUtils {

	/**
	 * @param roman
	 * @return long
	 * 
	 *         This method converts Roman to number.
	 */
	public static long romanToNumber(final String roman) {
		long l = 0;
		final Map<String, Integer> romanMap = getRomanMap();
		for (int i = roman.length(); i > 0; i--) {
			final int last = romanMap.get(roman.substring(i - 1, i));
			l += last;
			if (i > 1) {
				final int secondLast = romanMap.get(roman.substring(i - 2, i - 1));
				if (secondLast < last) {
					l -= secondLast;
					i--;
				}
			}
		}
		return l;
	}

	/**
	 * @return Map
	 * 
	 *         return map with Roman and respective number mappings.
	 */
	public static Map getRomanMap() {
		final Map<String, Integer> romanMap = new HashMap<String, Integer>();
		romanMap.put("I", 1);
		romanMap.put("V", 5);
		romanMap.put("X", 10);
		romanMap.put("L", 50);
		romanMap.put("C", 100);
		romanMap.put("D", 500);
		romanMap.put("M", 1000);
		return romanMap;
	}

	/**
	 * @param roman
	 * @return boolean
	 * 
	 *         This method returns true if the given string is valid Roman
	 *         number otherwise false.
	 */
	public static boolean isValidRoman(final String roman) {
		return null == roman || Pattern.compile(IConstants.VALID_ROMAN).matcher(roman).find();
	}
}

package com.galaxy.constant;

/**
 *
 * Common interfaces holds all constant variables used in application.
 *
 */
public interface IConstants {

	/** Regular expression for credit statements. */
	public static String REGEX_NUMBER = "is *[0-9]+";

	/** Regular expression for unit conversion statements. */
	public static String REGEX_ROMAN = "is *[MDCLXVI]+";

	/** Regular expression for query statements. */
	public static String REGEX_STMNT = "^how | [?]";

	/** Regular expression for question mark. */
	public static String REGEX_QN = " *[?]";

	/** Regular expression for eliminating all except numbers. */
	public static String REGEX_NUM_ONLY = "[^0-9]+";

	/** Regular expression for validation Roman numbers. */
	public static String VALID_ROMAN = "^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";

	/** IS string. */
	public static String IS = " is ";

	/** space. */
	public static String SPACE = " ";

	/** blank. */
	public static String BLANK = "";

	/** message. */
	public static String CREDITS = " Credits";

	/** message. */
	public static String INPUT = " INPUT :\n";

	/** message. */
	public static String OUTPUT = " OUTPUT :\n";

	/** new line. */
	public static String NEW_LINE = "\n";

	/** Error messages. */
	public static String MSG_0 = "(Error M0) : Cannot able to process the following statements, please provide at least one unit conversion data along with statements to process.";
	public static String MSG_1 = "(Error M1) : Please provide at least one unit conversion data along with statements to process.";
	public static String MSG_2 = "(Error M2) : Please provide at least one credit information.";
	public static String MSG_3 = "(Error M3) : No query statements to proceed.";
	public static String MSG_4 = "(Error M4) : Invalid conversion statement : ";
	public static String MSG_5_1 = "(Error M5) : The unit(s) provided  in \"";
	public static String MSG_5_2 = "\" statement is/are invalid.";
	public static String MSG_6 = "(Error M6) : Invalid unit combination : ";
	public static String MSG_7 = "(Error M7) : Invalid or no credits given : ";
	public static String MSG_8 = "(Error M8) : Invalid credit statement : ";
	public static String MSG_9 = "(Error M9) : I have no idea what you are talking about.";

}

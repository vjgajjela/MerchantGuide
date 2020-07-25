package com.galaxy.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

import com.galaxy.constant.IConstants;
import com.galaxy.exception.InvalidRomanException;
import com.galaxy.exception.InvalidStatementException;
import com.galaxy.model.InputOutput;
import com.galaxy.model.MerchantCredits;
import com.galaxy.model.MerchantQueries;
import com.galaxy.model.MerchantUnit;

/**
 * Implementation class the processes input file provided by user.
 */
public class MerchantImpl implements Merchant {

	/** Units model. */
	private final MerchantUnit units;

	/** Credits model. */
	private final MerchantCredits credits;

	/** Queries model. */
	private final MerchantQueries queries;

	/** Input Output model. */
	private final InputOutput inputOutput;

	/** Input file path. */
	private final String filePath;

	/** Constructor. */
	private MerchantImpl(final String filePath) {
		this.filePath = filePath;
		this.units = new MerchantUnit();
		this.credits = new MerchantCredits();
		this.queries = new MerchantQueries();
		this.inputOutput = new InputOutput();
	}

	/**
	 * @param filePath
	 * @return Merchant
	 *
	 *         method creates and returns Merchant implementation object.
	 */
	public static Merchant getMerchant(final String filePath) {
		return new MerchantImpl(filePath);
	}

	/**
	 *
	 * This method executes all process task in merchant guide application.
	 */
	@Override
	public void execute() {
		try {

			// reading input file to identify
			// 1) Unit conversion statements
			// 2) Credit statements
			// 3) Queries
			// preparing separate list for each.
			this.readStatements();

			// creating unit value map.
			this.units.createUnitValueMap();

			// creating subject credit map by using unit value map.
			this.credits.createSubjectCreditMap(this.units.getUnitValueMap());

			// processing queries using unit value and subject credit mappings.
			final String result = this.queries.compileQueries(this.units.getUnitValueMap(),
					this.credits.getSubjectCreditMap());

			// set result
			this.inputOutput.getResult().append(result);

		} catch (final InvalidStatementException e) {
			// Invalid statement message.
			this.inputOutput.getResult().append(e.getMessage()).append(IConstants.NEW_LINE);
		} catch (final InvalidRomanException e) {
			// Invalid Roman message.
			this.inputOutput.getResult().append(e.getMessage()).append(IConstants.NEW_LINE);
		}

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.galaxy.impl.Merchant#getInput()
	 *
	 * Returns input provided by user.
	 */
	@Override
	public String getInput() {
		return this.inputOutput.getInputText().toString();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.galaxy.impl.Merchant#getOutput()
	 *
	 * Returns output processed and prepared by application.
	 */
	@Override
	public String getOutput() {
		return this.inputOutput.getResult().toString();
	}

	private void readStatements() throws InvalidStatementException {
		// Roman matching pattern
		final Pattern romanPattern = Pattern.compile(IConstants.REGEX_ROMAN);
		// Number matching pattern
		final Pattern creditsPattern = Pattern.compile(IConstants.REGEX_NUMBER);
		// Query matching pattern
		final Pattern stmntPattern = Pattern.compile(IConstants.REGEX_STMNT);
		// message
		final StringBuilder result = new StringBuilder();

		BufferedReader br = null;
		try {
			String sCurrentLine;

			if (this.filePath != null) {
				// to run from UI
				br = new BufferedReader(new FileReader(this.filePath));
			} else {
				// to run from console
				br = new BufferedReader(new InputStreamReader(System.in));
			}
			sCurrentLine = br.readLine();

			while (sCurrentLine != null) {
				this.inputOutput.getInputText().append(sCurrentLine).append(IConstants.NEW_LINE);
				sCurrentLine = sCurrentLine.trim();
				if (romanPattern.matcher(sCurrentLine).find()) {
					this.units.getUnitStmnts().add(sCurrentLine);
				} else if (creditsPattern.matcher(sCurrentLine).find()) {
					this.credits.getCreditStmnts().add(sCurrentLine);
				} else if (stmntPattern.matcher(sCurrentLine).find()) {
					this.queries.getQueryStmnts().add(sCurrentLine);
				} else if (sCurrentLine.length() > 0) {
					result.append(sCurrentLine).append(IConstants.NEW_LINE);
				}
				sCurrentLine = br.readLine();
			}

			if (result.length() > 0) {
				result.insert(0, IConstants.NEW_LINE).insert(0, IConstants.MSG_0);
				throw new InvalidStatementException(result.toString());
			}
			if (this.units.getUnitStmnts().isEmpty()) {
				// no units provided
				throw new InvalidStatementException(IConstants.MSG_1);
			}
			if (this.queries.getQueryStmnts().isEmpty()) {
				// no queries to proceed.
				throw new InvalidStatementException(IConstants.MSG_3);
			}

		} catch (final IOException e) {
			this.inputOutput.getResult().append(e.getMessage()).append(IConstants.NEW_LINE);
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (final IOException ex) {
				this.inputOutput.getResult().append(ex.getMessage()).append(IConstants.NEW_LINE);
			}
		}
	}
}

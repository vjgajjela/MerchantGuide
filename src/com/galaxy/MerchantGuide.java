package com.galaxy;

import javax.swing.SwingUtilities;

import com.galaxy.impl.Merchant;
import com.galaxy.impl.MerchantImpl;

/**
 * Class to Run merchant guide UI.
 */
public class MerchantGuide {

	public static void main(final String[] args) {

		runAppFromUI();
		// runAppFromConsole();
	}

	public static void runAppFromUI() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MerchantUI();
			}
		});
	}

	public static void runAppFromConsole() {
		final Merchant merchant = MerchantImpl.getMerchant(null);
		// Merchant guide execution
		merchant.execute();
		// System.out.println(merchant.getInput());
		System.out.println(merchant.getOutput());
	}
}
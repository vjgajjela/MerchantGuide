package com.galaxy;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.galaxy.constant.IConstants;
import com.galaxy.impl.Merchant;
import com.galaxy.impl.MerchantImpl;
import com.galaxy.util.MyFilter;

/**
 * Class for Merchant guide user interface.
 */
/**
 * @author Gajjela
 *
 **/
public class MerchantUI extends JFrame implements Action {

	/** serial version id. */
	private static final long serialVersionUID = 1205396225898284191L;

	/** input text area. */
	private final JTextArea input;

	/** output text area. */
	private final JTextArea output;

	/** file chooser. */
	private final JFileChooser chooser;

	/** title. */
	private static String TITLE = "Merchant Guide";

	/** command approve. */
	private static String CMD_APPROVE = "ApproveSelection";

	/** command cancel. */
	private static String CMD_CANCEL = "CancelSelection";

	/** command submit. */
	private static String CMD_SUBMIT = "Submit";

	/** Constructor. */
	public MerchantUI() {
		super(TITLE);
		// Input text area
		this.input = new JTextArea();
		this.input.setRows(20);
		this.input.setColumns(this.getMaximumSize().width / 3);
		this.input.setEditable(false);
		this.input.setLineWrap(true);
		this.input.setToolTipText(IConstants.INPUT);
		final JScrollPane inputScroll = new JScrollPane(this.input, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		// Output text area
		this.output = new JTextArea();
		this.output.setLineWrap(true);
		this.output.setRows(20);
		this.output.setColumns(this.getMaximumSize().width / 3);
		this.output.setEditable(false);
		this.output.setToolTipText(IConstants.OUTPUT);
		final JScrollPane outputScroll = new JScrollPane(this.output, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.chooser = new JFileChooser();
		this.chooser.setToolTipText("File chooser");
		this.chooser.setFileFilter(new MyFilter());
		this.add(inputScroll);
		this.add(outputScroll, BorderLayout.NORTH);
		this.add(this.chooser, BorderLayout.SOUTH);
		this.chooser.addActionListener(this);
		this.chooser.setSize(this.getMaximumSize().width / 3, this.getMaximumSize().height);
		this.setResizable(true);
		this.setLayout(new GridLayout());
		this.setSize(this.getMaximumSize());
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/*
	 * (non-Javadoc)
	 *
	 *
	 * Action listener
	 */
	@Override
	public void actionPerformed(final ActionEvent e) {

		final String command = e.getActionCommand();
		if (CMD_SUBMIT.equals(command)) {
		} else if (CMD_APPROVE.equals(command)) {
			final Merchant merchant = MerchantImpl.getMerchant(this.chooser.getSelectedFile().getAbsolutePath());
			// Merchant guide execution
			merchant.execute();
			this.input.setText(merchant.getInput());
			this.output.setText(merchant.getOutput());
		} else if (CMD_CANCEL.equals(command)) {
			System.exit(0);
		}
	}

	@Override
	public Object getValue(final String key) {
		return null;
	}

	@Override
	public void putValue(final String key, final Object value) {

	}

}

package com.galaxy.util;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * Class for file filter
 */
public class MyFilter extends FileFilter {

	/*
	 * overridden this method to accept on text files.
	 * 
	 * @see javax.swing.filechooser.FileFilter#accept(java.io.File)
	 */
	@Override
	public boolean accept(final File f) {
		return f.getAbsolutePath().endsWith(".txt") || f.isDirectory();
	}

	@Override
	public String getDescription() {
		return "";
	}

}

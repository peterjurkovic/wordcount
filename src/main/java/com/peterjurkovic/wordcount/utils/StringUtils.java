package com.peterjurkovic.wordcount.utils;

import java.util.Objects;
import java.util.regex.Pattern;

public class StringUtils {
	
	private StringUtils(){}
	
	private final static Pattern BASE_PUNCTUATIONS_MARKS_PATTERN = Pattern.compile("([:;\\*\"\\.,!,\\?\\(\\)]+|\\'\\s|\\s\\'|^\\'|\\'$)");
	private final static Pattern MULTIPLE_SPACES_PATTERN = Pattern.compile("\\s{2,}");
	private final static Pattern SHLASH_PATTERN = Pattern.compile("[/\\\\]+|\\'\\s+|\\s+\\'");
	
	
	/**
	 * Removes punctuation marks from given text.
	 * 
	 * Removes following characters: <pre>:;*"',.?!</pre>
	 * Following characters replaces by space: <pre>\/'</pre>
	 * Replaces all multi-whitespace characters by only one whitespace.  
	 * 
	 * Examples:
	 * <pre>
	 *  Input					Output
	 *  --------------------------------
	 * "Hi; Hello;"			= "Hi Hello"
	 * "It's great!"		= "It's great"
	 * "foo/bar."			= "foo bar"
	 * "foo  bar."			= "foo bar"
	 * "\"quoted\""			= "quoted"
	 * </pre>
	 * 
	 * @param text
	 * @return
	 */
	public static String removePunctuationMarks(String text){
		text = SHLASH_PATTERN.matcher( Objects.requireNonNull( text ) ).replaceAll(" ");
		text = BASE_PUNCTUATIONS_MARKS_PATTERN.matcher(text).replaceAll("");
		return MULTIPLE_SPACES_PATTERN.matcher(text).replaceAll(" ").trim();
	}
	
	
}

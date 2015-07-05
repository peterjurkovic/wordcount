package com.peterjurkovic.wordcount;

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
	 * 
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

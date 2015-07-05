package com.peterjurkovic.wordcount;

import java.util.ArrayList;
import java.util.Iterator;

@SuppressWarnings("serial")
public class PartialStringList extends ArrayList<String> {
	
	/**
     * {@inheritDoc}
     */
	public PartialStringList(){
		super();
	}
	
	/**
     * {@inheritDoc}
     */
	public PartialStringList(int initialCapacity){
		super(initialCapacity);
	}
	
	/**
     * Returns <tt>true</tt> if this list contains the given element which 
     * contains given substring. This method ignores elements which <b>are equal
     * to given value</b>.
     * 
     * E.g.:
     * In case the list contains: "abcd", "abc". 
     * <pre>
     * containsSubstrnigIgnoreEqual("a") 	= true
     * containsSubstrnigIgnoreEqual("ab") 	= true
     * containsSubstrnigIgnoreEqual("abc") 	= true
     * containsSubstrnigIgnoreEqual("abcd")	= false
     * containsSubstrnigIgnoreEqual(null)	= false
     * <pre>
     * 
     * @param o element whose presence in this list is to be tested
     * @return <tt>true</tt> if at least one element contains substring, ignoring equals
     */
	public boolean containsSubstrnigIgnoreEqual(String value){
		return containsSubstring(value, true);
	}
	
	
	
	/**
     * Returns <tt>true</tt> if this list contains the given element which 
     * contains given substring.
     * 
     * E.g.:
     * In case the list contains: "abcd", "abc". 
     * <pre>
     * containsSubstrnigIgnoreEqual("a") 	= true
     * containsSubstrnigIgnoreEqual("ab") 	= true
     * containsSubstrnigIgnoreEqual("abc") 	= true
     * containsSubstrnigIgnoreEqual("abcd")	= true
     * containsSubstrnigIgnoreEqual(null)	= false
     * <pre>
     * 
     * @param o element whose presence in this list is to be tested
     * @return <tt>true</tt> if at least one element contains substring
     */
	public boolean containsSubstring(String value) {
		return containsSubstring(value, false);
	}
	
	
	private boolean containsSubstring(final String value, final boolean ignoreEquals) {
		if (value == null) {
			return false;
		}

		final char[] term = value.toCharArray();
		final int[] prefixiesSuffxies = preProcessPattern(term);
		final Iterator<String> it = iterator();
		while (it.hasNext()) {
			String listValue = it.next();
			if(ignoreEquals && listValue.equals(value)){
				continue;
			}
			if (searchSubString(listValue.toCharArray(), term, prefixiesSuffxies)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Pre processes the pattern array based on proper prefixes and proper
	 * suffixes at every position of the array
	 * 
	 * @param pattern
	 *            word that is to be searched in the search string
	 * @return partial match table which indicates
	 */
	private int[] preProcessPattern(char[] pattern) {
		int i = 0, j = -1;
		int paternLength = pattern.length;
		int[] b = new int[paternLength + 1];

		b[i] = j;
		while (i < paternLength) {
			while (j >= 0 && pattern[i] != pattern[j]) {
				j = b[j];
			}
			i++;
			j++;
			b[i] = j;
		}
		return b;
	}

	/**
	 * Based on the pre processed array, search for the pattern in the text
	 * 
	 * Implementation of The Knuth-Morris-Pratt algorithm
	 * 
	 * @param text
	 *            text over which search happens
	 * @param pattern
	 *            pattern that is to be searched
	 * @return boolean true in case is text 
	 * 
	 * {@see http://jakeboxer.com/blog/2009/12/13/the-knuth-morris-pratt-algorithm-in-my-own-words/}
	 * {@see http://tekmarathon.com/2013/05/14/algorithm-to-find-substring-in-a-string-kmp-algorithm/}
	 * {@see https://en.wikipedia.org/wiki/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm}
	 * 
	 */
	private boolean searchSubString(char[] text, char[] pattern, int[] prefixiesSuffxies) {
		int i = 0, j = 0;
		// pattern and text lengths
		int patternLength = pattern.length;
		int textLength = text.length;

		while (i < textLength) {
			while (j >= 0 && text[i] != pattern[j]) {
				j = prefixiesSuffxies[j];
			}
			i++;
			j++;
			// a match is found
			if (j == patternLength) {
				// j = b[j];
				return true;
			}
		}
		return false;
	}
}

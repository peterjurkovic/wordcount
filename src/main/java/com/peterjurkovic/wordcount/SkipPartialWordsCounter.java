package com.peterjurkovic.wordcount;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Peter Jurkovic
 */
public class SkipPartialWordsCounter implements WordCounter {

	
	/**
	 * Return collection of words but ignore words which are partial matches of longer words.
	 * 
	 * E.g.
	 * <pre>
	 * a mate material may maybe right maybe
	 * <b>a</b> is discarded as it is contained in mate, material, may and maybe.
	 * <b>a</b> m<b>a</b>te m<b>a</b>teri<b>a</b>l m<b>a</b>y m<b>a</b>ybe right m<b>a</b>ybe
	 * 
	 * Mate is discarded as it is contained in material.
	 * A mate <b>mate</b>rial may maybe right maybe
	 * 
	 * May is discarded as it is contained in maybe
	 * A mate material may <b>may</b>be right <b>may</b>be
	 * 
	 * Result will be:
	 * 
	 * material: 1
	 * maybe: 2
	 * right: 1
	 * </pre>
	 * 
	 * Note: the method is <b>case sensitive</b>.
	 * 
	 * @param PartialStringList - the list which should be used for computation.
	 * @return Set<Word> which contains unique words and counts (excluding partials) 
	 * 			or empty collection, never null.
	 * 
	 */
	public Set<Word> count(PartialStringList stringWrodList) {
		if(stringWrodList == null || stringWrodList.isEmpty()){
			return Collections.emptySet();
		}
		final Map<String, Integer> wordMap = new HashMap<String, Integer>(stringWrodList.size());
		for(final String strWord : stringWrodList){
			if(!stringWrodList.containsSubstrnigIgnoreEqual(strWord)){
				Integer count = wordMap.get(strWord);
				if(count == null){
					wordMap.put(strWord, 1);
				}else{
					wordMap.put(strWord, count + 1);
				}
			}
		}
		return toSet(wordMap);
	}
	
	

	
	private Set<Word> toSet(final Map<String, Integer> wordMap){
		final Set<Word> wordList = new HashSet<Word>(wordMap.size());
		for (Map.Entry<String, Integer> entry : wordMap.entrySet()){
			wordList.add(new Word(entry.getKey(), entry.getValue()));
		}
		return wordList;
	}
}

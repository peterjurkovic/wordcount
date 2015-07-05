package com.peterjurkovic.wordcount.utils;

import java.util.Objects;
import java.util.regex.Pattern;

import com.peterjurkovic.wordcount.models.PartialStringList;

/**
 * The utility class which splits strings
 * 
 * @author Peter Jurkovic
 *
 */
public class StringSplitter {

	private final static Pattern MULTI_WHITE_SPACE_SEPARATOR = Pattern.compile("[\\r\\n\\s]+");
	
	private final Pattern separator;
	private final boolean omitPunctuation;
	private final boolean toLowerCase;
	
	
	private StringSplitter() {
		this(MULTI_WHITE_SPACE_SEPARATOR, false, false);
	}
	
	
	private StringSplitter(Pattern separator, boolean omitPunctuation, boolean toLowerCase) {
		this.separator = Objects.requireNonNull(separator, "The pattern can not be null.");
		this.omitPunctuation = omitPunctuation;
		this.toLowerCase = toLowerCase;
	}
	
	/** 
	 *  Returns splitter which uses whitespace separator 
	 *  
	 *  <pre>
	 *  Example:
	 *  {@link StringSplitter#useWhiteSpaceSeparator().split("abc  abcd \n abc")}
	 *  
	 *  Result
	 *  {@code ["abc", "abcd", "abc"]}
	 *  </pre>
	 */
	public static StringSplitter useWhiteSpaceSeparator(){
		return new StringSplitter(MULTI_WHITE_SPACE_SEPARATOR,  false, false);
	}
	
	
	/** 
	 *  Returns splitter which uses given regular expression 
	 *  
	 *  <pre>
	 *  Example:
	 *  {@link StringSplitter#use(",").split("abc, abcd,  abc")}
	 *  
	 *  Result
	 *  {@code ["abc", " abcd", "  abc"]}
	 *  </pre>
	 */
	public static StringSplitter use(final String regexPattern){
		return new StringSplitter(Pattern.compile(regexPattern),  false, false);
	}
	
	
	/** 
	 *  Returns splitter which uses given regular expression 
	 *  
	 *  <pre>
	 *  Example:
	 *  {@link StringSplitter#use(",").split("abc, abcd,  abc")}
	 *  
	 *  Result
	 *  {@code ["abc", " abcd", "  abc"]}
	 *  </pre>
	 *  
	 *  @return StringSplitter 
	 */
	public static StringSplitter use(final Pattern separator){
		Objects.requireNonNull(separator, "Separator can not be null");
		return new StringSplitter(separator, false, false);
	}
	
	
	
	/**
	 * Return splitter which omits punctuation.
	 *  
	 *  <pre>{@code 
	 * StringSplitter#useWhiteSpaceSeparator().omitPunctuation().split("You! calm down.")  
	 * result: ["You", "calm", "down"]
	 *  
	 * StringSplitter#useWhiteSpaceSeparator().omitPunctuation().split("It's great! Isn't it?")  
	 * result: ["It's", "great", "Isn't", "it"]
	 * 
	 * StringSplitter#useWhiteSpaceSeparator().omitPunctuation().split("\"easy\" 'peasy' (lemon)")  
	 * result: ["easy", "peasy", "lemon"]
	 *  }</pre> 	
	 *  
	 *  @return StringSplitter which omits punctuation
	 */
	public StringSplitter omitPunctuation(){
		return new StringSplitter(separator, true, toLowerCase);
	}
	
	
	
	/** 
	 *  Returns splitter which converts all characters to lower case
	 *  
	 *  <pre>
	 *  Example:
	 *  {@link StringSplitter#useWhiteSpaceSeparator().toLowerCase().split("Abc, ABCD,  abC")}
	 *  
	 *  Result
	 *  {@code ["abc", "abcd", "abc"]}
	 *  </pre>
	 *  
	 *  @return StringSplitter 
	 */
	public StringSplitter toLowerCase(){
		return new StringSplitter(separator, true, true);
	}
	
	
	/**
	 * Splits the given text.
	 *  
	 * @param text
	 * @return PartialStringList 
	 * @throws NullPointerException in case if given text null
	 */
	public PartialStringList split(String text){
		text = Objects.requireNonNull(text, "The text can not be null");
		
		if(omitPunctuation){
			text = StringUtils.removePunctuationMarks(text);
		}
		final String[] wordList = separator.split(text);
		final PartialStringList partialWordList = new PartialStringList(wordList.length);
		for(String word : wordList){
			partialWordList.add( toLowerCase ? word.toLowerCase() : word);
		}

		return partialWordList; 
	}

	
	
}

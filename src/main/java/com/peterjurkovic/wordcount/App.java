package com.peterjurkovic.wordcount;

import java.util.Collections;
import java.util.List;


/**
 * Word count application
 *
 */
public class App 
{
	
	
    public static void main( String[] args ){
    	countWords(args);
    }
    
    public static void countWords( String[] args ){
    	if(args.length == 0){
    		System.out.println("Please select text file");
    	}else{
    		try {
				String text = TextFileReader.read(args[0]);
				
				PartialStringList list =	StringSplitter
												.useWhiteSpaceSeparator()
												.omitPunctuation()
												.toLowerCase()
												.split(text);
				
				printCounts( new SkipPartialWordsCounter().count(list) );
			} catch (Exception e) {
				System.out.println("The file does not exist or is not a text file.");
			}
    	}
    }
    
    
    private static void printCounts(List<Word> wordList){
    	Collections.sort(wordList);
    	for(Word word : wordList){
    		System.out.println(word.getFirstLetterUp() + ": "  + word.count);
    	}
    }
}

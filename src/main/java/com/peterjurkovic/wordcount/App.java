package com.peterjurkovic.wordcount;

import java.util.Collections;
import java.util.List;

import com.peterjurkovic.wordcount.counter.SkipPartialWordsCounter;
import com.peterjurkovic.wordcount.counter.WordCounter;
import com.peterjurkovic.wordcount.model.PartialStringList;
import com.peterjurkovic.wordcount.model.Word;
import com.peterjurkovic.wordcount.utils.FileUtils;
import com.peterjurkovic.wordcount.utils.StringSplitter;


/**
 * Word count application
 *
 */
public class App 
{
	private static final WordCounter wordCounter = new SkipPartialWordsCounter();
	
    public static void main( String[] args ){
    	countWords(args);
    }
    
    private static void countWords( String[] args ){
    	if(args.length == 0){
    		System.out.println("Please select text file");
    	}else{
				final String text = readFile(args[0]);
				if(text != null && text.length() > 0){
					printCounts( wordCounter.count( toList(text) ) );
				}
    	}
    }
    
    private static PartialStringList toList(String text){
    	return StringSplitter
					.useWhiteSpaceSeparator()
					.omitPunctuation()
					.toLowerCase()
					.split(text);
    }
    
    private static String readFile(String path){
    	try {
			return FileUtils.read( path );
		} catch (Exception e) {
			System.out.println("The file does not exist or is not a text file.");
		}
    	return null;
    }
    
    
    private static void printCounts(List<Word> wordList){
    	Collections.sort(wordList);
    	for(Word word : wordList){
    		System.out.println(word.getFirstLetterUp() + ": "  + word.count);
    	}
    }
}

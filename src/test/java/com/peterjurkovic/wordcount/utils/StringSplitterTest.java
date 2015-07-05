package com.peterjurkovic.wordcount.utils;

import static org.junit.Assert.assertEquals;
import static com.peterjurkovic.wordcount.WordListBuilder.*;

import java.util.List;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.peterjurkovic.wordcount.utils.StringSplitter;

@RunWith(JUnitParamsRunner.class)
public class StringSplitterTest {
	
	@Test
	@Parameters(method = "getSplitTextUsigComma")
	public void shouldSplitTextUsigComma(String text, List<String> expectedWordList){
		
		List<String> actualWordList = StringSplitter.use(",").split(text); 
		
		assertEquals(expectedWordList.size(), actualWordList.size());
		assertEquals(expectedWordList, actualWordList);
	}
	
	
	@Test
	@Parameters(method = "getSplitTextAndKeepPunctuation")
	public void shouldSplitTextAndKeepPunctuation(String text, List<String> expectedWordList){
		
		List<String> actualWordList = StringSplitter.useWhiteSpaceSeparator().split(text); 
		
		assertEquals(expectedWordList.size(), actualWordList.size());
		assertEquals(expectedWordList, actualWordList);
	}
	
	
	@Test
	@Parameters(method = "getRemovePunctuationAndSplitText")
	public void shouldRemovePunctuationAndSplitText(String text, List<String> expectedWordList){
		
		List<String> actualWordList = StringSplitter
										.useWhiteSpaceSeparator()
										.omitPunctuation()
										.split(text); 
		
		assertEquals(expectedWordList.size(), actualWordList.size());
		assertEquals(expectedWordList, actualWordList);
	}
	
	
	@Test
	@Parameters(method = "getRemovePunctuationAndSplitTextAndConvertToLowerCase")
	public void shouldRemovePunctuationAndSplitTextAndConvertToLowerCase(String text, List<String> expectedWordList){
		
		List<String> actualWordList = StringSplitter
										.useWhiteSpaceSeparator()
										.omitPunctuation()
										.toLowerCase()
										.split(text); 
		
		assertEquals(expectedWordList.size(), actualWordList.size());
		assertEquals(expectedWordList, actualWordList);
	}
	
	@SuppressWarnings("unused")
	private static Object[] getSplitTextUsigComma(){
		return new Object[]{
				new Object[]{ "", toList("") },
				new Object[]{ "You,can,write", toList("You","can","write") },
				new Object[]{ "You! ,Calm, DOWN.", toList("You! ","Calm"," DOWN.") },
				new Object[]{ "First,  Second , Third", toList("First", "  Second ", " Third") },
				
		};
	}
	
	@SuppressWarnings("unused")
	private static Object[] getRemovePunctuationAndSplitTextAndConvertToLowerCase(){
		return new Object[]{
				new Object[]{ "", toList("") },
				new Object[]{ "You can write", toList("you","can","write") },
				new Object[]{ "You! Calm DOWN.", toList("you","calm","down") },
				new Object[]{ "First  Second   Third", toList("first", "second", "third") },
				new Object[]{ "First  SecONd \n  Third.", toList("first", "second", "third") },
				new Object[]{ "Hi!  first, second and (Third).", toList("hi", "first", "second", "and", "third") },
				new Object[]{ "\"EASY\" 'PEASY'", toList("easy", "peasy") },
				
		};
	}
	
	@SuppressWarnings("unused")
	private static Object[] getRemovePunctuationAndSplitText(){
		return new Object[]{
				new Object[]{ "", toList("") },
				new Object[]{ "You can write", toList("You","can","write") },
				new Object[]{ "You! calm down.", toList("You","calm","down") },
				new Object[]{ "First  Second   Third", toList("First", "Second", "Third") },
				new Object[]{ "First  Second \n  Third.", toList("First", "Second", "Third") },
				new Object[]{ "It's great! Isn't it?", toList("It's", "great", "Isn't", "it") },
				new Object[]{ "A mate material may maybe right maybe", toList("A", "mate", "material", "may", "maybe", "right", "maybe") },
				new Object[]{ "Hi!  first, second and (Third).", toList("Hi", "first", "second", "and", "Third") },
				new Object[]{ "\"easy\" 'peasy'", toList("easy", "peasy") },
				
		};
	}
	
	@SuppressWarnings("unused")
	private static Object[] getSplitTextAndKeepPunctuation(){
		return new Object[]{
				new Object[]{ "", toList("") },
				new Object[]{ "You can write", toList("You","can","write") },
				new Object[]{ "You! calm down.", toList("You!","calm","down.") },
				new Object[]{ "First  Second   Third", toList("First", "Second", "Third") },
				new Object[]{ "First  Second \n  Third.", toList("First", "Second", "Third.") },
		};
	}
		

	
}

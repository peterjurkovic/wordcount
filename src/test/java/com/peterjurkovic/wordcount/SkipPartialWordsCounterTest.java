package com.peterjurkovic.wordcount;

import static com.peterjurkovic.wordcount.WordListBuilder.*;

import java.util.Collection;
import java.util.List;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.peterjurkovic.wordcount.PartialStringList;
import com.peterjurkovic.wordcount.SkipPartialWordsCounter;
import com.peterjurkovic.wordcount.Word;
import com.peterjurkovic.wordcount.WordCounter;

@RunWith(JUnitParamsRunner.class)
public class SkipPartialWordsCounterTest {
	
	private final WordCounter wordcount = new SkipPartialWordsCounter();
	
	
	
	@Test
	@Parameters(method = "getWordList")
	public void souldSkipPartialWords(PartialStringList wordList, List<Word> expectedList){
		Collection<Word> actualList = wordcount.count(wordList); 
		
		assertThat("Expected: "+ expectedList + " but was: " + actualList, actualList.size(), is(expectedList.size()));
		assertThat("List equality without order", actualList, containsInAnyOrder(expectedList.toArray()));
	}
	
	
	@SuppressWarnings("unused")
	private static Object[] getWordList(){
		return new Object[]{ 
			new Object[]{ 
					toPartialList("a", "mate", "material", "may", "maybe", "right" ,"maybe"),
					createList().add("material", 1).add("maybe", 2).add("right", 1).get()
			},
			new Object[]{ 
					toPartialList("A", "mate", "material", "may", "maybe", "right" ,"maybe"),
					createList().add("A", 1).add("material", 1).add("maybe", 2).add("right", 1).get()
			},
			new Object[]{ 
					toPartialList("A"),
					createList().add("A", 1).get()
			},
			new Object[]{ 
					toPartialList("a", "ab", "abc", "abcd"),
					createList().add("abcd", 1).get()
			},
			new Object[]{ 
					toPartialList("a", "ab", "abc", "abcd", "abcd", "abcd", "abcd"),
					createList().add("abcd", 4).get()
			},
			new Object[]{ 
					toPartialList("0", "ab", "abc", "ebcd", "abcd", "abcd", "abcd"),
					createList().add("0", 1).add("ebcd", 1).add("abcd", 3).get()
			},
			new Object[]{ 
					toPartialList("d", "cd", "bcd", "abcd", "abcd"),
					createList().add("abcd", 2).get()
			},
			new Object[]{ 
					toPartialList("b", "bc", "abc", "abcd", "abcde"),
					createList().add("abcde", 1).get()
			},
				
		};
	}
	
}

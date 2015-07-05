package com.peterjurkovic.wordcount.model;

import static com.peterjurkovic.wordcount.WordListBuilder.createList;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Collections;
import java.util.List;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.peterjurkovic.wordcount.model.Word;

@RunWith(JUnitParamsRunner.class)
public class WordTest {

	
	@Test(expected = NullPointerException.class)
	public void shouldThrowExceptionOnNull(){
		new Word(null, 1);
	}
	
	
	@Test
	@Parameters(method = "getTransformFirstLetterUp")
	public void shouldTransformFirstLetterUp(Word word, String expectedResult){
		assertThat(word.getFirstLetterUp(),  equalTo( expectedResult ));
	}
	
	
	@Test
	@Parameters(method = "getOrderByValueDesc")
	public void shouldBeOrderByValueDesc(List<Word> input, List<Word> expectedOutput ){
		Collections.sort(input);
		assertThat( input, equalTo(expectedOutput) );
	}
	
	@SuppressWarnings("unused")
	private static Object[] getOrderByValueDesc(){
		return new Object[]{
				new Object[]{ 
					createList().add("a", 2).add("abc", 2).add("ab", 1).get() , 
					createList().add("abc", 2).add("ab", 1).add("a", 2).get()  
				},
				new Object[]{ 
					createList().add("a", 2).add("ac", 2).add("ab", 1).get() , 
					createList().add("ac", 2).add("ab", 1).add("a", 2).get()  
				},
		};
	}
	
	@SuppressWarnings("unused")
	private static Object[] getTransformFirstLetterUp(){
		return new Object[]{
				new Object[]{ new Word("", 1) , "" },
				new Object[]{ new Word("a", 1) , "A" },
				new Object[]{ new Word("test", 1) , "Test" },
				new Object[]{ new Word("T", 1) , "T" },
		};
	}
}

package com.peterjurkovic.wordcount.utils;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import static org.hamcrest.core.Is.*;
import static org.junit.Assert.assertThat;
import static com.peterjurkovic.wordcount.utils.StringUtils.*;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class StringUtilsTest {
	
	
	
	@Test
	@Parameters(method = "getRemoveSpecialCharactersStrings")
	public void testRemovingSpecialCharacters(String input, String expectedOutput){
		assertThat(removePunctuationMarks(input), is( expectedOutput ));
	}
		
	
	@Test(expected = NullPointerException.class)
	public void shouldThrowAnExceptionIfIsTextEmpty(){
		removePunctuationMarks( null );
	}
	
	
	
	@SuppressWarnings("unused")
	private static Object[] getRemoveSpecialCharactersStrings(){
		return new Object[]{
				new Object[]{ " ", "" },
				new Object[]{ "Hi. ", "Hi" },
				new Object[]{ "Hi  Ensemble! ", "Hi Ensemble" },
				new Object[]{ "Hi? ", "Hi" },
				new Object[]{ "Hi! ", "Hi" },
				new Object[]{ "Hi; Hello; ", "Hi Hello" },
				new Object[]{ "Hi/ Hello; ", "Hi Hello" },
				new Object[]{ "Hi / Hello; ", "Hi Hello" },
				new Object[]{ "It's great! ", "It's great" },
				new Object[]{ "Hi \\ Hello; ", "Hi Hello" },
				new Object[]{ "Hi\\Hello; ", "Hi Hello" },
				new Object[]{ "Hi (item, item2, item3) ", "Hi item item2 item3" },
				new Object[]{ "Hi/Hello; ", "Hi Hello" },
				new Object[]{ "Yes! Item, Item ", "Yes Item Item" },
				new Object[]{ "Following items: Item one, Item two! ", "Following items Item one Item two" },
				new Object[]{ "Hi. , . ", "Hi" },
				new Object[]{ " \"quoted\" ", "quoted" },
				new Object[]{ " 'quoted' ", "quoted" },
				new Object[]{ "'quoted'", "quoted" },
				new Object[]{ "Hi &50 ", "Hi &50" },
				new Object[]{ "\"easy\" 'peasy' ", "easy peasy" },
				new Object[]{ "Hi*  ", "Hi" },
				new Object[]{ "A mate material may maybe right maybe", "A mate material may maybe right maybe" }	
				
		};
	}
	
	
}

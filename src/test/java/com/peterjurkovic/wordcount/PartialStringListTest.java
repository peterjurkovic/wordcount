package com.peterjurkovic.wordcount;

import static com.peterjurkovic.wordcount.WordListBuilder.toPartialList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.peterjurkovic.wordcount.PartialStringList;

@RunWith(JUnitParamsRunner.class)
public class PartialStringListTest {
	
	
	@Test
	@Parameters(method = "getContainsSubstringsIngoreEqual")
	public void testContainsSubstringsIngoreEqual(PartialStringList list, String pattern, boolean expectedResult){
		assertThat(list.containsSubstrnigIgnoreEqual(pattern), is( expectedResult ));
	}
	
	
	@Test
	@Parameters(method = "getContainsSubstrings")
	public void testContainsSubstrings(PartialStringList list, String pattern, boolean expectedResult){
		assertThat(list.containsSubstring(pattern), is( expectedResult ));
	}
	
	
	@SuppressWarnings("unused")
	private static Object[] getContainsSubstringsIngoreEqual(){
		return new Object[]{
				new Object[]{ toPartialList("ahoj"), "ho", true },
				new Object[]{ toPartialList("A", "material"), "mate", true },
				new Object[]{ toPartialList("material"), "a", true },
				new Object[]{ toPartialList("regrettable"), "r", true },
				new Object[]{ toPartialList("regrettable"), "re", true },
				new Object[]{ toPartialList("regrettable"), "egr", true },
				new Object[]{ toPartialList("regrettable"), "tabl", true },
				new Object[]{ toPartialList("regrettable"), "table", true },
				
				new Object[]{ toPartialList("regrettable"), "regrettable", false },
				new Object[]{ toPartialList("test"), null, false },
				new Object[]{ toPartialList("test"), "Test", false },
				new Object[]{ toPartialList("test"), "a", false },
				new Object[]{ toPartialList("test"), "ES", false },
				new Object[]{ toPartialList("test"), "E S", false }
		};
	}
	
	
	@SuppressWarnings("unused")
	private static Object[] getContainsSubstrings(){
		return new Object[]{
				new Object[]{ toPartialList("ahoj"), "ho", true },
				new Object[]{ toPartialList("A", "material"), "mate", true },
				new Object[]{ toPartialList("material"), "a", true },
				new Object[]{ toPartialList("regrettable"), "r", true },
				new Object[]{ toPartialList("regrettable"), "re", true },
				new Object[]{ toPartialList("regrettable"), "egr", true },
				new Object[]{ toPartialList("regrettable"), "tabl", true },
				new Object[]{ toPartialList("regrettable"), "table", true },
				new Object[]{ toPartialList("regrettable"), "regrettable", true },
				
				new Object[]{ toPartialList("test"), null, false },
				new Object[]{ toPartialList("test"), "Test", false },
				new Object[]{ toPartialList("test"), "a", false },
				new Object[]{ toPartialList("test"), "ES", false },
				new Object[]{ toPartialList("test"), "E S", false }
		};
	}
	
	

	
}

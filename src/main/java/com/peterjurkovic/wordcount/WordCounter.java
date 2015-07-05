package com.peterjurkovic.wordcount;

import java.util.Collection;

public interface WordCounter {
	
	Collection<Word> count(PartialStringList wrodList);
}

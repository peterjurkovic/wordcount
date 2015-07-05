package com.peterjurkovic.wordcount;

import java.util.List;

public interface WordCounter {
	
	List<Word> count(PartialStringList wrodList);
}

package com.peterjurkovic.wordcount.counter;

import java.util.List;

import com.peterjurkovic.wordcount.model.PartialStringList;
import com.peterjurkovic.wordcount.model.Word;

public interface WordCounter {
	
	List<Word> count(PartialStringList wrodList);
}

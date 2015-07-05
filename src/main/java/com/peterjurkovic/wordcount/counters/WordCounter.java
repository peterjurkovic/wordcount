package com.peterjurkovic.wordcount.counters;

import java.util.List;

import com.peterjurkovic.wordcount.models.PartialStringList;
import com.peterjurkovic.wordcount.models.Word;

public interface WordCounter {
	
	List<Word> count(PartialStringList wrodList);
}

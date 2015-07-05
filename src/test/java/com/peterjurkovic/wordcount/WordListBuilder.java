package com.peterjurkovic.wordcount;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.peterjurkovic.wordcount.model.PartialStringList;
import com.peterjurkovic.wordcount.model.Word;

public class WordListBuilder {
	
	private List<Word> wordList = new ArrayList<Word>();

	public static List<String> toList(final String ... words){
		return Arrays.asList(words);
	}
	
	public static List<String> toPartialList(final String ... words){
		PartialStringList list = new PartialStringList(words.length);
		list.addAll(toList(words));
		return list;
	}
	
	
	public static WordListBuilder createList(){
		return new WordListBuilder();
	}
	
	
	public WordListBuilder add(String word, int count){
		wordList.add(new Word(word, count));
		return this;
	}
	
	public List<Word> get(){
		return wordList;
	}
	
}

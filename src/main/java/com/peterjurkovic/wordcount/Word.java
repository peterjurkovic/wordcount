package com.peterjurkovic.wordcount;

import java.util.Objects;

public class Word implements Comparable<Word>{
	
	public final String value;
	public final int count;
	
	
	public Word(final String value, final int count){
		this.value = Objects.requireNonNull(value, "Value of word can not be null");
		this.count = count;
	}
	
	
	public String getFirstLetterUp(){
		if(value.length() == 0){
			return value;
		}
		return value.substring(0,1).toUpperCase() + value.substring(1);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + count;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Word other = (Word) obj;
		if (count != other.count)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "["+value +": " + count + "x]";
	}

	
	public int compareTo(Word word) {
		return word.value.length() - value.length();
	}



	
}

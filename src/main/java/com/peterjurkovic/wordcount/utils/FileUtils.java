package com.peterjurkovic.wordcount.utils;

import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 
 * @author 
 *
 */
public class FileUtils {
	
	private FileUtils(){}
	
	
	/**
	 * Read text file from given path.
	 * 
	 * Every line is separated using whitespace. In case the file on given path
	 * does not exists or is binary file. 
	 * 
	 * @param filepath
	 * @return String content of the file
	 * @throws Exception - in case the file on given path does not exist
	 * 					 or is not readable
	 */
	public static String read(final String filepath) throws Exception{
		Path file = Paths.get(filepath);
        BufferedReader reader = Files.newBufferedReader(file, Charset.defaultCharset());
        StringBuilder fileContent = new StringBuilder();
        String line = null;
		while ((line = reader.readLine()) != null) {
		    fileContent.append(line).append(" ");
		}
        return fileContent.toString();
	}
}

package com.peterjurkovic.wordcount;

import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TextFileReader {
	
	
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

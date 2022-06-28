package com.ikhokha.techcheck;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class spamCheck implements Rule_Interface 
{
	private boolean containsURL(String Comment) 
	{
		String urlRegex = "\\b((https?|ftp)://|(www|ftp)\\.)?[a-z0-9-]+(\\.[a-z0-9-]+)+([/?].*)?\\b";
		Pattern pattern = Pattern.compile(urlRegex);
		Matcher matcher = pattern.matcher(Comment);
		return matcher.find();
	}
	
	@Override
	public Map<String, Integer> Analyze(File CommentFile)
	{
		Map<String, Integer> ResultsMap = new HashMap<>();
		
		try (BufferedReader Reader = new BufferedReader(new FileReader(CommentFile)))
		{
			String Line = null;
			boolean isFound = false;
			
			while ((Line = Reader.readLine()) != null) 
			{
				isFound = containsURL(Line);
				if (isFound) 
				{
					incOccurrence(ResultsMap, "SPAM");				
				}
			}
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("File " + CommentFile.getName() + " Not Found. File Path: " + CommentFile.getAbsolutePath());
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			System.out.println("I/O Error Processing File: " + CommentFile.getAbsolutePath());
			e.printStackTrace();
		}
		
		return ResultsMap;
	}
}

package com.ikhokha.techcheck;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MoverMention implements Rule_Interface
{
	@Override
	public Map<String, Integer> Analyze(File CommentFile)
	{
		Map<String, Integer> ResultsMap = new HashMap<>();
		
		try (BufferedReader Reader = new BufferedReader(new FileReader(CommentFile)))
		{
			String Line = null;
			while ((Line = Reader.readLine()) != null) 
			{
				if (Line.toLowerCase().contains("mover")) 
				{
					incOccurrence(ResultsMap, "MOVER_MENTIONS");				
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

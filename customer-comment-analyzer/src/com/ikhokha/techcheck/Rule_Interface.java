package com.ikhokha.techcheck;

import java.io.File;
import java.util.Map;

public interface Rule_Interface 
{
	Map<String, Integer> Analyze(File CommentFile);
	
	/**
	 * This method increments a counter by 1 for a match type on the countMap. Uninitialized keys will be set to 1
	 * @param countMap the map that keeps track of counts
	 * @param key the key for the value to increment
	 */
	default Map<String, Integer> incOccurrence(Map<String, Integer> countMap, String key) 
	{		
		countMap.putIfAbsent(key, 0);
		countMap.put(key, countMap.get(key) + 1);
		return countMap;
	}
}
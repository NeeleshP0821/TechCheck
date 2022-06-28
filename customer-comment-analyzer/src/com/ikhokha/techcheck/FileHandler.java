package com.ikhokha.techcheck;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class FileHandler implements Runnable 
{
	private File[] file;
	Map<String, Integer> totalResults = new HashMap<>();
	
	public FileHandler(File[] CommentFiles) 
	{
		this.file = CommentFiles;
	}	
	
	@Override
	public void run() 
	{
		for (File CommentFile : file) 
		{				
			CommentLength commentlength = new CommentLength();			
			Map<String, Integer> fileResults = commentlength.Analyze(CommentFile);
			addReportResults(fileResults, totalResults);
			fileResults.clear();
			
			MoverMention movermention = new MoverMention();
			fileResults = movermention.Analyze(CommentFile);
			addReportResults(fileResults, totalResults);
			fileResults.clear();
			
			ShakerMention shakermention = new ShakerMention();
			fileResults = shakermention.Analyze(CommentFile);
			addReportResults(fileResults, totalResults);
			fileResults.clear();
			
			Questions questions = new Questions();
			fileResults = questions.Analyze(CommentFile);
			addReportResults(fileResults, totalResults);
			fileResults.clear();
			
			spamCheck spam = new spamCheck();
			fileResults = spam.Analyze(CommentFile);
			addReportResults(fileResults, totalResults);
			fileResults.clear();
		}
		
		System.out.println("RESULTS\n=======");
		totalResults.forEach((k,v) -> System.out.println(k + " : " + v));
	}
	
	/**
	* This method adds the result counts from a source map to the target map 
	* @param source the source map
	* @param target the target map
	*/	
	private synchronized void addReportResults(Map<String, Integer> source, Map<String, Integer> target) 
	{
		for (Map.Entry<String, Integer> entry : source.entrySet()) 
		{
			target.merge(entry.getKey(), entry.getValue(),(oldval,newval) -> oldval + newval);
		}
	}
}

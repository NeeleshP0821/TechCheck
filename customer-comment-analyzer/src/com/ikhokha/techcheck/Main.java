package com.ikhokha.techcheck;

import java.io.File;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;

public class Main
{
	public static void main(String[] args) throws InterruptedException
	{		
		File docPath = new File("docs");
		File[] CommentFiles = docPath.listFiles((d, n) -> n.endsWith(".txt"));
		
		ThreadPoolExecutor threadPoolExecutor = 
				new ThreadPoolExecutor(10,20,60,TimeUnit.SECONDS,new LinkedBlockingDeque<>(CommentFiles.length),
						new CallerRunsPolicy());
		
	
		threadPoolExecutor.execute(new FileHandler(CommentFiles));
		
		threadPoolExecutor.shutdown();
	}		
}
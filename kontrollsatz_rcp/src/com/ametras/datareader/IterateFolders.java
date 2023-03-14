package com.ametras.datareader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

public class IterateFolders {

	String folderDirectory = "C:\\Users\\mmahmoud\\Desktop\\Rel7_Workspace\\rewe-rcp";
//	String folderDirectory = "C:\\Users\\Anwender\\Desktop\\Arbeit\\Rel7_Workspace\\rewe-rcp";
	
	
	private ArrayList<String> listJavaFiles = new ArrayList<String>();	
	private int count=0;

	public ArrayList<String> getListJavaFiles() throws IOException  {
	  Path dir = Paths.get(folderDirectory);
	  Files.walk(dir).forEach(path -> showFile(path.toFile()));	
	  return listJavaFiles;
	}
	
	  private void showFile(File file) {
	        if (file.isDirectory()) {
	        	if (file.getPath().endsWith(".java")) {	
	        		System.out.println("Directory: " + file.getAbsolutePath() + StringUtils.SPACE + count);   
	        	}	  
	        	
	        } else {
	        	if (file.getPath().endsWith(".java")) {
	        		listJavaFiles.add(file.getAbsolutePath());
		            System.out.println("File: " + file.getAbsolutePath() + StringUtils.SPACE + count);        		
	        	}
	        }		
	        count++;
	  } 
}

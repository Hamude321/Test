package alt;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class IterateFolders {

	private static int count=0;
	private static ArrayList<String> ListJavaFiles = new ArrayList<String>();
	
	public static void main(String[] args) throws IOException {
	    Path dir = Paths.get("C:\\Users\\mmahmoud\\Desktop\\Rel7_Workspace\\rewe-rcp");
	    Files.walk(dir).forEach(path -> showFile(path.toFile()));	
		System.out.println(ListJavaFiles.size());
	}
	
		
	  public static void showFile(File file) {
	        if (file.isDirectory()) {
	        	if (file.getPath().endsWith(".java")) {
	        		System.out.println("Directory: " + file.getAbsolutePath() + " " + count);   
	        		count++;
	        	}	  
	        	
	        } else {
	        	if (file.getPath().endsWith(".java")) {
	        		ListJavaFiles.add(file.getAbsolutePath());
		            System.out.println("File: " + file.getAbsolutePath() + " " + count);
		            count++;	        		
	        	}
	        }			  
	  } 
	  
	  public ArrayList<String> getListJavaFiles() {
		  return ListJavaFiles;
	  }
	  

}

//package com.stackoverflow.q3154488;
//
//import java.io.File;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//public class DemoWithJava8 {
//
//    public static void main(String... args) throws Exception {
//        Path dir = Paths.get("/path/to/dir");
//        Files.walk(dir).forEach(path -> showFile(path.toFile()));
//    }
//
//    public static void showFile(File file) {
//        if (file.isDirectory()) {
//            System.out.println("Directory: " + file.getAbsolutePath());
//        } else {
//            System.out.println("File: " + file.getAbsolutePath());
//        }
//    }
//}
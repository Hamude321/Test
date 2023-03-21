package com.ametras.datareader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.ametras.controlrecord.view.FilesData;

public class IterateFolders {


//	String folderDirectory = "C:\\Users\\mmahmoud\\Desktop\\Rel7_Workspace\\rewe-rcp";

		
	private ArrayList<String> listJavaFiles = new ArrayList<String>();	
	private int count=0;

	public ArrayList<String> getListJavaFiles() throws IOException  {
	  IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
	  IViewPart viewPart=null;
	try {
		viewPart = page.showView("com.ametras.controlrecord.FilesData");
	} catch (PartInitException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  FilesData filesData;
	  filesData = (FilesData) viewPart;
	  String folderDirectory = filesData.getfolderDirectory();
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

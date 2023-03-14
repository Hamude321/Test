package alt;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FoldersIterate {

	private int count=0;
	private ArrayList<String> ListJavaFiles = new ArrayList<String>();
	
	
	private void files() throws IOException  {
	  Path dir = Paths.get("C:\\Users\\mmahmoud\\Desktop\\Rel7_Workspace\\rewe-rcp");
	  Files.walk(dir).forEach(path -> showFile(path.toFile()));	
	}
	
	  private void showFile(File file) {
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
		  try {
			files();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return ListJavaFiles;
	  }
}

package alt;
//package com.ametras.datareader;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.lang3.SystemUtils;
//import org.apache.commons.lang3.tuple.ImmutablePair;
//import org.apache.commons.lang3.tuple.Pair;
//
//public class blubb {
//		
//	public static void main(String[] args) {	
//		
//		JavaReader reader = new JavaReader();
//		
//		//Get List of all Java Files contained in the folder
//	    IterateFolders Folder = new IterateFolders();	    
//	    try {
//			Folder.files();
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//	    ArrayList<String> ListJavaFiles = Folder.getListJavaFiles();
//	    
//	    //Get List of all Program Names in the .csv files
//	    CSVReader ProgramNames = new CSVReader();	
//	    ProgramNames.ReadCsv();
//	    ArrayList<String> ListProgramNames = ProgramNames.getListProgramNames();
//	    
//	    //List of all the Program Names that were found in the Java Files
//	    ArrayList<String> ListFoundPrograms = new ArrayList<String>();
//	    
//	    //Make copy of ListProgramNames to later remove Duplicates
//	    ArrayList<String> ListUnusedPrograms = ListProgramNames;
//	    
//	    //List of all found pairs
//		ArrayList<Pair<String, String>> PairList = new ArrayList<>();
//		
//		//Temporary dump list
//		ArrayList<Pair<String, String>> DumpList = new ArrayList<>();
//
//		
//	    int count =0;
//	    TxtOutput txt = new TxtOutput();
//	    
//	    //Iterate all Java Files in ArrayList
//	    for (String filepath : ListJavaFiles) {	       
//		    String line = "";  
//		    try {			   
//			    //parsing a CSV file into BufferedReader class constructor  
//			    BufferedReader br = new BufferedReader(new FileReader(filepath));  
//			    
//			    //Key String for finding Program name and Value
//			    String SearchCondition ="ReweSessionHolder.getConfigurationValue(";
//			    int methodFirstIndex;
////			    int methodLastIndex = SearchCondition.lastIndexOf("(");
//			    int methodLastIndex = StringUtils.lastIndexOf(SearchCondition, "(");
//			
//			    List <String> found = new ArrayList<>();
//			    
//				//To-Do 
//			    //(if line starts with //, dont consider it)
//			    while ((line = br.readLine()) != null) {   //returns a Boolean value  
//			    
//			    	//Search for the line that contains the specified values
//			    	//To-Do		    		
//				    if (StringUtils.contains(line, SearchCondition) && !StringUtils.trim(line).startsWith("//")) {
//				    	found.add(line);
//				    	methodFirstIndex = StringUtils.indexOf(line, SearchCondition);				    	
//
//				    	//Cuts the string to right after the first (
//				    	String cutLine = StringUtils.substring(line, methodFirstIndex + methodLastIndex + 1);				    	
//				    	String[] test = StringUtils.split(cutLine, ", ");	
////				    	try {
////				    		System.out.println(test[0] + " " + test[1] + "       " + line.trim() +  "       " + filepath);
////				    		txt.WriteToTxtFile(test[0] + " " + test[1] + "       " + line.trim() +  "       " + filepath);
////				    	}
////				    	catch(Exception e){
////				    		System.out.println("Out of Bounds  " + "       " + line + "       " + filepath);
////				    	}
//				    	
//				    	try {
//				    		Pair<String, String> pair = reader.findValue(test[0], test[1]);				    					    		
//				    		PairList.add(pair);
//				    		txt.WriteToTxtFile(pair.getLeft()+" " + pair.getRight());
//				    		
//				    	}
//				    	catch(Exception e){
//				    		System.out.println("Out of Bounds  " + "       " + line + "       " + filepath);
//				    	}				    	
//				    }
//				    
//				   //Search for the Program Name in current line. Add to list if found.
//				   for(String i : ListProgramNames) {
//					   if(StringUtils.contains(line, i)) {
//						   ListFoundPrograms.add(i);
//					   }
//				   }				    
//			    } 			    
//			    br.close();
//		    }   
//		    catch (IOException e) {      
//		    	e.printStackTrace();  
//		    }  
//		    count++;
//	}
//	    System.out.println(ListJavaFiles.size()+" "+count);
//	    System.out.println("Total Number: " + ListProgramNames.size());
//	    System.out.println("Found: " + CSVReader.removeDuplicates(ListFoundPrograms).size());
//	    ListUnusedPrograms.removeAll(ListFoundPrograms);
//	    System.out.println("Unused: " + ListUnusedPrograms.size());
////	    System.out.println(ListProgramNames);
//	    System.out.println(PairList);
////	    System.out.println(PairList.get(0).getLeft());
//	    System.out.println("DONE");
//	}
//	
//	//Trace back values to their original state
//	public Pair<String, String> findValue(String CTRPGM, String CTRSAN) {
//		
//		//Determine if CTRPGM is a function, variable or wanted value
//		//function
//		if(StringUtils.contains(CTRPGM, ".") || StringUtils.contains(CTRPGM, "(")) {
//			//To-Do
//			System.out.println("Function: " + CTRPGM);
//		}
//		//value
//		else if (StringUtils.contains(CTRPGM, "\"")) {
//				CTRPGM = StringUtils.replaceChars(CTRPGM, "\"", StringUtils.EMPTY);
//			System.out.println("Keine Variable: " + CTRPGM);
//		}
//		//variable
//		else {
//			//To-Do
//			//Liste komplett durchgehen und CTRPGM+ = +"" ==1;
//			System.out.println("Variable: " + CTRPGM);
//		}		
//		
//		//Determine if CTRPGM is a function, variable or wanted value		#
//		if(StringUtils.contains(CTRSAN, ".") && StringUtils.contains(CTRSAN, "(")) {
//			//To-Do
//			System.out.println("Function: " + CTRSAN);
//		}
//		else if(StringUtils.isNumeric(CTRSAN)) {
//			//To-Do
////			if(CTRSAN.length()>3) {
////				System.out.println("größer 3: " + CTRSAN+ "Länge: "+ CTRSAN.length());
////			}
//		}
//		else {
//			//To-Do
//		}
//		return Pair.of(CTRPGM,CTRSAN);
//	}
//}

package com.ametras.datareader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

public class JavaReader {
    
	//klasse, zeile, code?
	//147
	//multithread
	//printWithAReport
	//EnumAReportConfig
	//KORR
	//ctrList?
	//class dateipfad
	//if check für filepath
	//gleichzeitig iterieren
	
    //List of all found pairs
	private static ArrayList<Pair<String, String>> pairList = new ArrayList<>();
	private static ArrayList<ControlRecord> ctrList = new ArrayList<>();
    private TxtOutput txt = new TxtOutput();
    //List of all the Program Names that were found in the Java Files
    private static ArrayList<String> listFoundPrograms = new ArrayList<String>();
    private static ArrayList<String> listProgramNames;
    
    private ArrayList<ControlRecord> mergedObjectsList;
    private ArrayList<ControlRecord> undefinedList = new ArrayList<>();;
		
	public void read() throws IOException {	
		
		JavaReader reader = new JavaReader();	
		
		//Get List of all Java Files contained in the folder
	    IterateFolders folder = new IterateFolders();	    
	    final ArrayList<String> listJavaFiles = folder.getListJavaFiles();
	    
	    //Get List of all Program Names in the .csv files
	    CSVReader csv = new CSVReader();
	    final ArrayList<ControlRecord> listCsvData = csv.getListControlRecords(); 
	    listProgramNames = csv.getListProgramNamesNoDuplicates();
	    
	    //Make copy of ListProgramNames to later remove Duplicates
	    ArrayList<String> listUnusedPrograms = listProgramNames;
	    ArrayList<ControlRecord> foundAndConfirmed;
	    	    
	    
		//Iterate lines for String
	    for (String filepath : listJavaFiles) {	 
	    	reader.fileSearchLines("ReweSessionHolder.getConfigurationValue(", filepath);    	
	    	//**************************************************
	    	reader.fileSearchLines("configService.getConfigValue(", filepath);
	    	//**************************************************
	    }
	   
	    //Sort pairList by Program Name
	    pairList.sort(Comparator.comparing(p -> p.getLeft()));	
	    foundAndConfirmed = reader.compareLists(listCsvData, CSVReader.removeDuplicates(pairList));
	    mergedObjectsList = reader.combineCtr(foundAndConfirmed, ctrList); /*-------------------*/
	    
	    
	    System.out.println("Java Files: " + listJavaFiles.size());
	    System.out.println("Total Programs: " + listProgramNames.size());
	    System.out.println("Unique Programs: " + CSVReader.removeDuplicates(listFoundPrograms).size());
	    listUnusedPrograms.removeAll(listFoundPrograms);
	    System.out.println("Unused Programs: " + listUnusedPrograms.size());
	    System.out.println("Pairs Found: " + pairList.size());
//	    System.out.println("Pairs Found: " + CSVReader.removeDuplicates(pairList));
	    System.out.println("Pairs Found no duplicates: " + (CSVReader.removeDuplicates(pairList)).size());
	    
	    
	    System.out.println("Confirmed: " + foundAndConfirmed.size()); 

	    TxtOutput txt = new TxtOutput();
	    for(ControlRecord o :foundAndConfirmed){
	    	txt.WriteToTxtFile(o.pgm + " " + o.san, "found.txt");
	    }
	    
	    System.out.println(mergedObjectsList.size());	  
	    	
	    System.out.println("DONE");	    
	}
	
	public void fileSearchLines(String search, String filepath) {

	    String line = "";  
	    try {			   
		    BufferedReader br = new BufferedReader(new FileReader(filepath));  		    
		    
		    if (StringUtils.contains(search, "ReweSessionHolder.getConfigurationValue(") || StringUtils.contains(search, "configService.getConfigValue(")) {
		    	
		    	String values[];
			    int methodFirstIndex = 0;
			    int methodLastIndex = StringUtils.lastIndexOf(search, "(");

			    while ((line = br.readLine()) != null) {  			      		
				    if (StringUtils.contains(line, search) && !StringUtils.trim(line).startsWith("//") && !StringUtils.trim(line).startsWith("/*") && !StringUtils.trim(line).startsWith("*")) {
				    	methodFirstIndex = StringUtils.indexOf(line, search);				    	
				    	String brackets = StringUtils.substring(line, methodFirstIndex + methodLastIndex + 1);	
				    	
				    	if(StringUtils.trim(line).endsWith("getConfigurationValue(")){				    			
				    		 values = StringUtils.split(br.readLine(), ", ");
				    	}
				    	else {
					    	values = StringUtils.split(brackets, ", ");	
				    	}

				    	try {
				    		Pair<String, String> pair = findValue(values[0], values[1], filepath);				    					    		
				    		pairList.add(pair);			
				    		
				    		String className = StringUtils.substring(filepath, StringUtils.lastIndexOf(filepath, "\""), StringUtils.lastIndexOf(filepath, "."));
				    		
				    		ControlRecord ctr = new ControlRecord("0", pair.getLeft(), pair.getRight(),"", "");
				    				    		
				    		compareCtrForFilepath(ctr, filepath);
				    		ctrList.add(ctr);
				    		//**************
				    					    		
				    	}
				    	catch(Exception e){
				    		txt.WriteToTxtFile(StringUtils.trim(line) + " " , "outofbounds.txt");
				    		System.out.println("Out of Bounds  " + "       " + line + "       " + filepath);
				    	}			    	
				    }				    
					   //Search for the Program Name in current line. Add to list if found.
					   for(String i : listProgramNames) {
						   if(StringUtils.contains(line, i)) {
							   listFoundPrograms.add(i);
						   }
					   }	
			    }	    	
		    }			    
		    br.close();			    
	    }   
	    catch (IOException e) {      
	    	e.printStackTrace();  
	    }  
}
	
	//Find corresponding value to the constant.
	public String findConstant(String search, String filepath){
    	String value = "NotFound: " + search;
	    String line = StringUtils.EMPTY;  
	    try {
			BufferedReader br = new BufferedReader(new FileReader(filepath));			
	    	String[] variable;
	    	
	    	try {
				while ((line = br.readLine()) != null) {
					if(StringUtils.contains(line, "String" + StringUtils.SPACE + search + StringUtils.SPACE + "=" + StringUtils.SPACE)) {
						if(listProgramNames.contains(search)) {
							value = search;
						}
						else {
							variable = StringUtils.split(line, "\"");
							value = variable[1] ; // add/delete *
						}
						
					}
					if(StringUtils.contains(line, "Integer" + StringUtils.SPACE + search + StringUtils.SPACE + "=" + StringUtils.SPACE) || StringUtils.contains(line, "int" + StringUtils.SPACE + search + StringUtils.SPACE + "=" + StringUtils.SPACE)) {						
						variable = StringUtils.split(line, "=");	
						variable = StringUtils.split(variable[1], StringUtils.SPACE);
						value = StringUtils.replace(variable[0], ";", StringUtils.SPACE); 
						variable = StringUtils.split(value, StringUtils.SPACE);
						value = variable[0];
						value = StringUtils.trim(value) ; // add/delete *	
						
//						if(!StringUtils.isNumeric(value)) {
//							go again
//						}						
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return value;  
	}
	
	//Check if CTRPGM/CTRSAN is function, variable, value.
	public Pair<String, String> findValue(String CTRPGM, String CTRSAN, String filepath) {
		JavaReader reader1 = new JavaReader();
		//function
		if(StringUtils.contains(CTRPGM, ".") || StringUtils.contains(CTRPGM, "(")) {
			if(StringUtils.contains(CTRPGM, "Constants")) {
				String c[] = StringUtils.split(CTRPGM, ".");
				CTRPGM = c[1];				
			}
			else {
				CTRPGM = "(" + CTRPGM + ")";
			}		
			System.out.println("Function: " + CTRPGM);
		}
		//value
		else if (StringUtils.contains(CTRPGM, "\"")) {
			CTRPGM = StringUtils.replaceChars(CTRPGM, "\"", StringUtils.EMPTY);
			System.out.println("Keine Variable: " + CTRPGM);
		}
		//variable
		else {
			CTRPGM = findConstant(CTRPGM, filepath);
			System.out.println("Variable: " + CTRPGM);
		}		
		
		//function
		if(StringUtils.contains(CTRSAN, ".") && StringUtils.contains(CTRSAN, "(")) {
			CTRSAN = "(" + CTRSAN + ")";
			System.out.println("Function: " + CTRSAN);
		}
		
		//value
		else if(StringUtils.isNumeric(CTRSAN)) {
			if(StringUtils.length(CTRSAN)>3) {
				CTRSAN = StringUtils.overlay(CTRSAN, ".", StringUtils.length(CTRSAN)-3, StringUtils.length(CTRSAN)-3);
			}
			System.out.println("Keine Variable: " + CTRSAN);
		}
		//variable
		else {
			CTRSAN = reader1.findConstant(CTRSAN, filepath);
			if(StringUtils.isNumeric(CTRSAN) && StringUtils.length(CTRSAN)>3) {
				CTRSAN = StringUtils.overlay(CTRSAN, ".", StringUtils.length(CTRSAN)-3, StringUtils.length(CTRSAN)-3);
			}
			System.out.println("Variable: " + CTRSAN);
		}
		return Pair.of(CTRPGM,CTRSAN);
	}
	
	//Compare provided List with List of found objects
	public ArrayList<ControlRecord> compareLists(ArrayList<ControlRecord> listOfCsvObj, ArrayList<Pair<String,String>> pair){
		ArrayList<ControlRecord> newList = new ArrayList<>();
		ArrayList<Pair<String,String>> notFoundList = new ArrayList<>();
				
		for (Pair<String, String> p : pair) {
			for (ControlRecord obj : listOfCsvObj) {
				if(StringUtils.equalsIgnoreCase(p.getLeft(), obj.pgm) && StringUtils.equals(p.getRight(), obj.san)) {
					newList.add(obj);
					notFoundList.add(Pair.of(p.getLeft(), p.getRight()));
					continue;
				}							
			}
		}	
		pair.removeAll(notFoundList);
		
		for(Pair<String, String> x : pair) {
			ControlRecord cr = new ControlRecord("", x.getLeft(), x.getRight(),"","");
			undefinedList.add(cr);
		}	
		return newList;
	}
	
	
	public String getPackageName(String filepath) throws FileNotFoundException, IOException {
	    String line = StringUtils.EMPTY;  
	    String variable[] = null;
		try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
			while ((line = br.readLine()) != null) {
				if(StringUtils.contains(line, "package") && !StringUtils.trim(line).startsWith("//") && !StringUtils.trim(line).startsWith("/*") && !StringUtils.trim(line).startsWith("*")) {
					variable = StringUtils.split(line, StringUtils.SPACE);
					return variable[1];
				}
			}
			br.close();
		}	
		return StringUtils.EMPTY;
	}
	
	public ArrayList<ControlRecord> combineCtr(ArrayList<ControlRecord> shortList, ArrayList<ControlRecord> longList){
		ArrayList<ControlRecord> newList  = new ArrayList<>();
	    for(ControlRecord s : shortList) {
	    	for(ControlRecord l : longList) {
	    		if(StringUtils.equalsIgnoreCase(l.getPgm(), s.getPgm()) && StringUtils.equals(l.getSan(), s.getSan())) {
	    			l.setFir(s.getFir());
	    			l.setFld(s.getFld());
	    			l.setTxt(s.getTxt());
	    			newList.add(l);
	    			continue;
	    		}
	    	}	
	    }
		return newList;
	}
	
	public void compareCtrForFilepath(ControlRecord ctr, String filepath){
		ArrayList<String> ListFilepath = new ArrayList<>();
	    for(ControlRecord c : ctrList) {
	    		if(StringUtils.equalsIgnoreCase(c.getPgm(), ctr.getPgm()) && StringUtils.equals(c.getSan(), ctr.getSan())) {
	    			ListFilepath = c.getFilepath();
	    			ListFilepath.add(filepath);
	    			c.setFilepath(ListFilepath);
	    			continue;
	    		}
	    	}	
	    }

	
	
	public ArrayList<ControlRecord> getAllFoundElements(){
		return ctrList;
	}
	
	public ArrayList<ControlRecord> getFoundAndExistingElements(){
		return mergedObjectsList;
	}
	
	public ArrayList<ControlRecord> getAllNotFoundElements(){
		CSVReader csvreader = new CSVReader();
		ArrayList<ControlRecord> tempList = new ArrayList<>();
		try {
			tempList = csvreader.getListControlRecords();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		tempList.removeAll(getFoundAndExistingElements());		
		return tempList;
	}

	public ArrayList<ControlRecord> getUndefinedElements(){				
		return undefinedList;
	}
}

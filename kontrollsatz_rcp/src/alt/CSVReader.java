//package com.ametras.datareader;
//import java.io.BufferedReader;  
//import java.io.FileReader;  
//import java.io.IOException;
//import java.util.ArrayList;
//
//import org.apache.commons.lang3.StringUtils;   
//
//public class CSVReader {
//
//	
//	private  ArrayList<String> ctrProgramNameList = new ArrayList<String>();
//	
//	public ArrayList<String> getListProgramNames(){		
//	    String csvDirectory="C:\\Users\\mmahmoud\\Desktop\\Kontrollsätze_Workspace\\FIBCTR.csv";        
//	    String line = StringUtils.EMPTY;  
//	    String splitBy = ";";  
//	    
//	    try { 
//		    BufferedReader br = new BufferedReader(new FileReader(csvDirectory));  
//		    int count=1;
//		    while ((line = br.readLine()) != null) {  	    	
//			    String[] ctr = StringUtils.split(line, splitBy);   
//			    System.out.println("Program=" + ctr[1] + ", SNr=" + ctr[2] + StringUtils.SPACE + count); 
//			    count++;
//			    ctrProgramNameList.add(ctr[1]);
//		    }  
//		    br.close();
//	    }   
//	    catch (IOException e) {  
//	    	e.printStackTrace();  
//	    }	    
//	    ctrProgramNameList = removeDuplicates(ctrProgramNameList);
//	    return ctrProgramNameList;
//	}
//	
//
//	//Removes duplicates from an ArrayList
//    public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list) { 
//        ArrayList<T> newList = new ArrayList<T>(); 
//
//        for (T element : list) { 
//            if (!newList.contains(element)) { 
//                newList.add(element); 
//            } 
//        }   
//        return newList; 
//    } 
//}

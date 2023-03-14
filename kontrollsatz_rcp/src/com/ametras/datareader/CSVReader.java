package com.ametras.datareader;

import java.io.FileReader;  
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
 
public class CSVReader {

	
	private ArrayList<String> ctrProgramNameList = new ArrayList<String>();
	private ArrayList<ControlRecord> controlRecordList = new ArrayList<ControlRecord>();
	
	public ArrayList<ControlRecord> getListControlRecords() throws IOException{		  
	    String csvDirectory="C:\\Users\\mmahmoud\\Desktop\\Kontrollsätze_Workspace\\FIBCTR.csv";  
//	    String csvDirectory="C:\\Users\\Anwender\\Desktop\\Arbeit\\Kontrollsätze_Workspace\\FIBCTR.csv"; 
	      
	   TxtOutput txt = new TxtOutput();
	    
		Reader in = new FileReader(csvDirectory);
		Iterable<CSVRecord> records = CSVFormat.EXCEL.withDelimiter(';').parse(in);
		for (CSVRecord record : records) {
		    String CTRFIR = record.get(0);
		    String CTRPGM = record.get(1);
		    String CTRSAN = record.get(2);
		    String CTRFLD = record.get(3);
		    String CTRTXT = record.get(4);
		    
		    ControlRecord controlRecord = new ControlRecord(CTRFIR, CTRPGM, CTRSAN, CTRFLD, CTRTXT);
		    controlRecordList.add(controlRecord);
		    ctrProgramNameList.add(CTRPGM);
		    
		    txt.WriteToTxtFile(CTRPGM + " " + CTRSAN, "csv.txt");
		}   
	    return controlRecordList;   
	}
	
	public ArrayList<String> getListProgramNamesNoDuplicates(){
		return removeDuplicates(ctrProgramNameList); 
	}
	
	//Removes duplicates from an ArrayList
    public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list) { 
        ArrayList<T> newList = new ArrayList<T>(); 

        for (T element : list) { 
            if (!newList.contains(element)) { 
                newList.add(element); 
            } 
        }   
        return newList; 
    } 
    

    
}

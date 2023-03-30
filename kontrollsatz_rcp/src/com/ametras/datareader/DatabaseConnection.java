package com.ametras.datareader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

public class DatabaseConnection {
	
	Integer ctrfir;
	String ctrpgm;
	Integer ctrsan;
	String ctrfld;
	String ctrtxt;
	
	private static ArrayList<String> ctrProgramNameList = new ArrayList<String>();
	private static ArrayList<ControlRecord> controlRecordList = new ArrayList<ControlRecord>();

	public void getListControlRecordsFromDb() {
        try {
            Class.forName("com.ibm.as400.access.AS400JDBCDriver");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Please include Classpath Where your DB2 Driver is located");
            e.printStackTrace();
        }
        System.out.println("DB2 driver is loaded successfully");
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        boolean found = false;
        try {
            conn = DriverManager.getConnection("jdbc:as400://aline.ametras.com", "mma", "mma");
            if (conn != null)
            {
                System.out.println("DB2 Database Connected");
                System.out.println("Connected with: " + conn);
            }
            else
            {
                System.out.println("Db2 connection Failed ");
            }
            pstmt = conn.prepareStatement("Select * from AMERED710.FIBCTR where CTRFIR = 0");
            rset = pstmt.executeQuery();
            if(rset != null)
            {
                while(rset.next())
                {
                    found = true;
//                    System.out.println(rset.getInt("ctrfir") + " " + rset.getString("ctrpgm") + " " + rset.getInt("ctrsan"));
                    ctrfir = rset.getInt("ctrfir");
                    ctrpgm = rset.getString("ctrpgm");
                    ctrsan = rset.getInt("ctrsan");
                    ctrfld = rset.getString("ctrfld");
                    ctrtxt = rset.getString("ctrtxt");
                    
        		    ControlRecord controlRecord = new ControlRecord(ctrfir.toString(), StringUtils.trim(ctrpgm), ctrsan.toString(), ctrfld, ctrtxt);
        		    controlRecordList.add(controlRecord);
        		    ctrProgramNameList.add(ctrpgm);
                }
            }
            if (found == false)
            {
                System.out.println("No Information Found");
            }
        } catch (SQLException e) {
            System.out.println("DB2 Database connection Failed");
            e.printStackTrace();
        }
        try {
			conn.close();
			System.out.println("Connection closed");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	public static ArrayList<String> getListProgramNamesNoDuplicates(){
		return removeDuplicates(ctrProgramNameList); 
	}
		
	public static ArrayList<ControlRecord> getControlRecordList() {
		return controlRecordList;
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

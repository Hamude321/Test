package com.ametras.datareader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class Database2 {

	public static void main(String[] argv) {
        try {
            Class.forName("com.ibm.as400.access.AS400JDBCDriver");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Please include Classpath Where your DB2 Driver is located");
            e.printStackTrace();
            return;
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
                    System.out.println(rset.getInt("ctrfir") + " " + rset.getString("ctrpgm") + " " + rset.getInt("ctrsan"));
                }
            }
            if (found == false)
            {
                System.out.println("No Information Found");
            }
        } catch (SQLException e) {
            System.out.println("DB2 Database connection Failed");
            e.printStackTrace();
            return;
        }
        try {
			conn.close();
			System.out.println("Connection closed");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

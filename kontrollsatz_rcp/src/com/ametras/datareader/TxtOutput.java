package com.ametras.datareader;

import java.io.FileOutputStream;
import java.io.PrintStream;

public class TxtOutput {
	
	public void WriteToTxtFile(String line, String name) {
        FileOutputStream out; // declare a file output object
        PrintStream p; // declare a print stream object

        try {
			out = new FileOutputStream(name, true);
			p = new PrintStream(out);
			
			p.append (line+"\n");
			p.close();
        }
        catch (Exception e) {
            System.err.println ("Error writing to file");
        }
	}
}

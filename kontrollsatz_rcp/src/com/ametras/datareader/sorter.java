package com.ametras.datareader;

import java.io.IOException;
import java.util.ArrayList;

public class sorter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<ControlRecord> list = new ArrayList<>();

		CSVReader reader = new CSVReader();
		try {
			list = reader.getListControlRecords();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		list.sort((o1, o2) -> o1.getTxt().compareTo(o2.getTxt()));
		
		for(ControlRecord c : list) {
			System.out.println(c.txt);
		}
	}

}

package com.ametras.datareader;

import java.util.ArrayList;

public class ControlRecord {
	String fir;
	String pgm;
	String san;
	String fld;
	String txt;
	String className;
	String packageName;
	ArrayList<String> filepath = new ArrayList<>();
	
	public ControlRecord(String pgm, String san) {
		this.pgm=pgm;
		this.san=san;
	}
	
	public ControlRecord(String fir, String pgm, String san, String fld, String txt) {
		this.fir=fir;
		this.pgm=pgm;
		this.san=san;
		this.fld=fld;
		this.txt=txt;	
	}
	
	public ControlRecord(String fir, String pgm, String san, String fld, String txt, ArrayList<String> filepath ) {
		this.fir=fir;
		this.pgm=pgm;
		this.san=san;
		this.fld=fld;
		this.txt=txt;	
		this.filepath=filepath;
	}
	

//	public ControlRecord(String fir, String pgm, String san, String fld, String txt, String className, String packageName, String filepath ) {
//		this.fir=fir;
//		this.pgm=pgm;
//		this.san=san;
//		this.fld=fld;
//		this.txt=txt;	
//		this.className=className;
//		this.packageName=packageName;
//		this.filepath=filepath;
//	}
	
	
	public String getFir() {
		return fir;
	}

	public String getPgm() {
		return pgm;
	}

	public String getSan() {
		return san;
	}

	public String getFld() {
		return fld;
	}

	public String getTxt() {
		return txt;
	}

	public String getClassName() {
		return className;
	}

	public String getPackageName() {
		return packageName;
	}

	public ArrayList<String> getFilepath() {
		return filepath;
	}

	public void setFilepath(ArrayList<String> filepath) {
		this.filepath = filepath;
	}

	public void setFir(String fir) {
		this.fir = fir;
	}

	public void setFld(String fld) {
		this.fld = fld;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	@Override
	public boolean equals(Object x) {
		ControlRecord test = (ControlRecord) x;
		if(	this.fir.equalsIgnoreCase(test.fir) && this.fir.equalsIgnoreCase(test.fir)){
			return true;
		}
		return false;
	}

	
}

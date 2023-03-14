package com.ametras.controlrecord.view;

import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

import com.ametras.datareader.CSVReader;
import com.ametras.datareader.ControlRecord;
import com.ametras.datareader.JavaReader;

public class DataBaseWindow
{
    private Shell shell;
    private static Table table;
    
    
    public DataBaseWindow()
    {
        shell = new Shell(Display.getCurrent());
        shell.setSize(986, 548);

    	TableViewer tableViewer = new TableViewer(shell, SWT.BORDER | SWT.FULL_SELECTION);
    	table = tableViewer.getTable();
    	table.setBounds(10, 10, 931, 478);       
    	
    	DataBaseWindow.fillTableDB(tableViewer);	
    }

   
	private static void fillTableDB(TableViewer tableViewer) {
		
	CSVReader reader = new CSVReader();
	ArrayList<ControlRecord> csv=null;
	try {
		csv = reader.getListControlRecords();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	tableViewer.setContentProvider(ArrayContentProvider.getInstance());
	
//	createColumns(tableViewer)
	table.setHeaderVisible(true);
	table.setLinesVisible(true);
	
	TableViewerColumn ctrpgm = new TableViewerColumn(tableViewer, SWT.NONE);
	ctrpgm.getColumn().setWidth(100);
	ctrpgm.getColumn().setText("CTRPGM");
	ctrpgm.setLabelProvider(new ColumnLabelProvider() {
	    @Override
	    public String getText(Object element) {
	        ControlRecord p = (ControlRecord) element;
	        return p.getPgm();
	    }
	});
	TableViewerColumn ctrsan = new TableViewerColumn(tableViewer, SWT.NONE);
	ctrsan.getColumn().setWidth(100);
	ctrsan.getColumn().setText("CTRSAN");
	ctrsan.setLabelProvider(new ColumnLabelProvider() {
	    @Override
	    public String getText(Object element) {
	        ControlRecord p = (ControlRecord) element;
	        return p.getSan();
	    }
	});
	TableViewerColumn ctrfld = new TableViewerColumn(tableViewer, SWT.NONE);
	ctrfld.getColumn().setWidth(100);
	ctrfld.getColumn().setText("CTRFLD");
	ctrfld.setLabelProvider(new ColumnLabelProvider() {
	    @Override
	    public String getText(Object element) {
	        ControlRecord p = (ControlRecord) element;
	        return p.getFld();
	    }
	});
	TableViewerColumn ctrtext = new TableViewerColumn(tableViewer, SWT.NONE);
	ctrtext.getColumn().setWidth(620);
	ctrtext.getColumn().setText("CTRTXT");
	ctrtext.setLabelProvider(new ColumnLabelProvider() {
	    @Override
	    public String getText(Object element) {
	        ControlRecord p = (ControlRecord) element;
	        return p.getTxt();
	    }
	});
	tableViewer.setInput(csv);
	}

    public void open()
    {
        shell.open();
    }

    public void close()
    {
        shell.setVisible(false);
    }
}

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

public class DetailsWindow {
    private Shell shell;
    private static Table table;
    
    
    public DetailsWindow(ControlRecord ctr)
    {
        shell = new Shell(Display.getCurrent());
        shell.setSize(986, 548);

    	TableViewer tableViewer = new TableViewer(shell, SWT.BORDER | SWT.FULL_SELECTION);
    	table = tableViewer.getTable();
    	table.setBounds(10, 10, 931, 478);       
    	
    	DetailsWindow.fillTableDetails(tableViewer, ctr);	
    }

   
	private static void fillTableDetails(TableViewer tableViewer, ControlRecord ctr) {
		
	ArrayList<String> filepath = null;
	filepath = ctr.getFilepath();

	tableViewer.setContentProvider(ArrayContentProvider.getInstance());
	
//	createColumns(tableViewer)
	table.setHeaderVisible(true);
	table.setLinesVisible(true);
	
	TableViewerColumn className = new TableViewerColumn(tableViewer, SWT.NONE);
	className.getColumn().setWidth(1000);
	className.getColumn().setText("Class");
	className.setLabelProvider(new ColumnLabelProvider() {
	});
	
//	TableViewerColumn packageName = new TableViewerColumn(tableViewer, SWT.NONE);
//	packageName.getColumn().setWidth(100);
//	packageName.getColumn().setText("Package");
//	packageName.setLabelProvider(new ColumnLabelProvider() {
//	    @Override
//	    public String getText(Object element) {
//	        ArrayList<String> p = (ArrayList<String>) element;
//	        return p.get(0);
//	    }
//	});

	tableViewer.setInput(filepath);
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

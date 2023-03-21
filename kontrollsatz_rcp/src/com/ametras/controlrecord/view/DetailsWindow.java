package com.ametras.controlrecord.view;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class DetailsWindow {
    private Shell shell;
    private static Table table;
    private Text txt_class;
    
    
    public DetailsWindow(ControlRecord ctr)
    {
        shell = new Shell(Display.getCurrent());
        shell.setSize(986, 548);

    	TableViewer tableViewer = new TableViewer(shell, SWT.BORDER | SWT.FULL_SELECTION);
    	table = tableViewer.getTable();
    	table.setBounds(10, 72, 931, 416);       
    	
    	DetailsWindow.fillTableDetails(tableViewer, ctr);	
    	
    	Label lbl_class = new Label(shell, SWT.NONE);
    	lbl_class.setBounds(10, 31, 55, 15);
    	lbl_class.setText("Class");
    	
    	txt_class = new Text(shell, SWT.BORDER);
    	txt_class.setBounds(82, 28, 76, 21);
    }

   
	private static void fillTableDetails(TableViewer tableViewer, ControlRecord ctr) {
		
	ArrayList<String> filepath = null;
	filepath = CSVReader.removeDuplicates(ctr.getFilepath());
	
	

	tableViewer.setContentProvider(ArrayContentProvider.getInstance());
	
//	createColumns(tableViewer)
	table.setHeaderVisible(true);
	table.setLinesVisible(true);
	
	TableViewerColumn className = new TableViewerColumn(tableViewer, SWT.NONE);
	className.getColumn().setWidth(250);
	className.getColumn().setText("Class");
	className.setLabelProvider(new ColumnLabelProvider() {
		@Override
		public String getText(Object element) {
			String p = (String) element;
			return StringUtils.substring(p, StringUtils.lastIndexOf(p, "\\")+1, StringUtils.lastIndexOf(p, "."));
		}
	});
	
	TableViewerColumn packageName = new TableViewerColumn(tableViewer, SWT.NONE);
	packageName.getColumn().setWidth(600);
	packageName.getColumn().setText("Package");
	packageName.setLabelProvider(new ColumnLabelProvider() {
		@Override
		public String getText(Object element) {
			String p = (String) element;
			String a = StringUtils.substring(p, StringUtils.lastIndexOf(p, "\\src\\main\\java\\"), StringUtils.lastIndexOf(p, "."));
			return StringUtils.substring(a, StringUtils.lastIndexOf(a, "java")+5, StringUtils.lastIndexOf(a, "\\"));
		}
	});

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

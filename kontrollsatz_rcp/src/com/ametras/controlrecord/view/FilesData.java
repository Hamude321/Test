package com.ametras.controlrecord.view;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.ui.part.ViewPart;
import com.ametras.datareader.ControlRecord;
import com.ametras.datareader.JavaReader;
import org.eclipse.swt.widgets.Table;

import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class FilesData extends ViewPart {
	private String folderDirectory=StringUtils.EMPTY;
	private static Table table;
	private Text txt_ctrpgm;
	private Text txt_ctrsan;
	private Text txt_ctrfld;
	private Text txt_ctrtext;
	private static JavaReader reader = new JavaReader();

	public FilesData() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void createPartControl(Composite parent) {
		
		parent.getShell().setSize(1000, 620);			
		
		//Composites
		Composite composite = new Composite(parent, SWT.NONE);
		
		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setBounds(10, 10, 957, 106);
				
		//Initialize Tableviewer
		TableViewer tableViewer = new TableViewer(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				TableItem item = table.getItem(table.getSelectionIndex());
				ControlRecord selectedValue = (ControlRecord) item.getData();
				
				DetailsWindow detailsWindow = new DetailsWindow(selectedValue);
				detailsWindow.open();
			}
		});
		table.setBounds(10, 143, 957, 395);
		
		//Labels
		Label lbl_ctrpgm = new Label(composite_1, SWT.NONE);
		lbl_ctrpgm.setBounds(10, 63, 55, 15);
		lbl_ctrpgm.setText("CTRPGM");
		
		Label lbl_ctrsan = new Label(composite_1, SWT.NONE);
		lbl_ctrsan.setText("CTRSAN");
		lbl_ctrsan.setBounds(169, 63, 55, 15);
		
		Label lbl_fld = new Label(composite_1, SWT.NONE);
		lbl_fld.setText("CTRFLD");
		lbl_fld.setBounds(332, 63, 55, 15);
		
		Label lbl_ctrtext = new Label(composite_1, SWT.NONE);
		lbl_ctrtext.setText("CTRTEXT");
		lbl_ctrtext.setBounds(487, 63, 55, 15);
		
		Label lbl_laden = new Label(composite, SWT.NONE);
		lbl_laden.setText("Suche starten");
		lbl_laden.setToolTipText("");
		lbl_laden.setBounds(10, 122, 79, 15);

		//Text 
		txt_ctrpgm = new Text(composite_1, SWT.BORDER);
		txt_ctrpgm.setBounds(70, 60, 76, 21);		
		
		txt_ctrsan = new Text(composite_1, SWT.BORDER);
		txt_ctrsan.setBounds(230, 60, 76, 21);
		
		txt_ctrfld = new Text(composite_1, SWT.BORDER);
		txt_ctrfld.setBounds(393, 60, 76, 21);
		
		txt_ctrtext = new Text(composite_1, SWT.BORDER);
		txt_ctrtext.setBounds(553, 60, 76, 21);
		
		//Buttons
		Button btn_datenbank = new Button(composite_1, SWT.NONE);
		btn_datenbank.setBounds(225, 8, 96, 25);
		btn_datenbank.setText("Datenbank");		
		
		Button btn_suchen = new Button(composite_1, SWT.NONE);
		btn_suchen.setBounds(10, 8, 75, 25);
		btn_suchen.setText("Suchen");		

		//TableViewer
		tableViewer.setContentProvider(ArrayContentProvider.getInstance());
		
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
			
		TableViewerColumn ctrpgm = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tableColumn = ctrpgm.getColumn();

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
		TableColumn tableColumn_1 = ctrsan.getColumn();

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
		TableColumn tableColumn_2 = ctrfld.getColumn();

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
		TableColumn tableColumn_3 = ctrtext.getColumn();

		ctrtext.getColumn().setWidth(650);
		ctrtext.getColumn().setText("CTRTXT");
		ctrtext.setLabelProvider(new ColumnLabelProvider() {
		    @Override
		    public String getText(Object element) {
		        ControlRecord p = (ControlRecord) element;
		        return p.getTxt();		        
		    }
		    
		});
		
		//Combo Filter
		Combo combo_filter = new Combo(composite_1, SWT.READ_ONLY);
		combo_filter.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				if(StringUtils.equals(combo_filter.getText(), "Alle")) {
					ArrayList<ControlRecord> records = reader.getAllFoundElements();					
					tableViewer.setInput(records);
				}
				else if(StringUtils.equals(combo_filter.getText(), "Gefunden")) {				
					ArrayList<ControlRecord> records = reader.getFoundAndExistingElements();
					tableViewer.setInput(records);
				}
				else if(StringUtils.equals(combo_filter.getText(), "Nicht gefunden")) {				
					ArrayList<ControlRecord> records = reader.getAllNotFoundElements();
					tableViewer.setInput(records);
				}
				else if(StringUtils.equals(combo_filter.getText(), "Nicht zugeordnet")){
					ArrayList<ControlRecord> records = reader.getAllFoundElements();
					for(ControlRecord c : reader.getFoundAndExistingElements()) {
						Predicate<ControlRecord> condition = p->p.getPgm().equals(c.getPgm()) && p.getSan().equals(c.getSan());
						records.removeIf(condition);
					}
					for(ControlRecord c : reader.getAllNotFoundElements()) {
						Predicate<ControlRecord> condition = p->p.getPgm().equals(c.getPgm()) && p.getSan().equals(c.getSan());
						records.removeIf(condition);
					}
					tableViewer.setInput(records);
				}
				else {					
				}
			}
		});
		combo_filter.setItems(new String[] {"Alle", "Gefunden", "Nicht gefunden", "Nicht zugeordnet"});
		combo_filter.setBounds(671, 60, 91, 23);
		combo_filter.setText("Alle");
		
		Button btn_ordner = new Button(composite_1, SWT.NONE);
		btn_ordner.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				DirectoryDialog dialogue = new DirectoryDialog(parent.getShell());
				folderDirectory = dialogue.open();
				System.out.println(folderDirectory);
			}
		});
		btn_ordner.setBounds(111, 8, 91, 25);
		btn_ordner.setText("Ordner wählen");
		
		Button btn_filter = new Button(composite_1, SWT.NONE);
		btn_filter.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ArrayList<ControlRecord> records = new ArrayList<>();
				switch(combo_filter.getText()) {				
				case "Alle": 
					records = reader.getAllFoundElements(); 
					tableViewer.setInput(filterElementsAsList(records));
					break;
				case "Gefunden": 
					records = reader.getFoundAndExistingElements(); 
					tableViewer.setInput(filterElementsAsList(records));
					break;
				case "Nicht gefunden": 
					records = reader.getAllNotFoundElements(); 
					tableViewer.setInput(filterElementsAsList(records));
					break;
				case "Nicht zugeordnet": 
					records = reader.getUndefinedElements();
					tableViewer.setInput(filterElementsAsList(records));
					break;			
				}
			}
		});
		
			
		btn_filter.setBounds(838, 60, 96, 25);
		btn_filter.setText("Filter anwenden");
				
		//Events		
		btn_datenbank.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				DataBaseWindow DataBaseWindow = new DataBaseWindow();
				DataBaseWindow.open();
			}
		});
					
		btn_suchen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {	
				try {
					lbl_laden.setText("Wird geladen...");
					reader.read();
					lbl_laden.setText("Fertig!");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ArrayList<ControlRecord> records = reader.getAllFoundElements();
				tableViewer.setInput(records);			
			}
		});
		
		tableColumn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(StringUtils.equals(combo_filter.getText(), "Alle")) {
					ArrayList<ControlRecord> records = reader.getAllFoundElements();	
					records.sort((o1, o2) -> o1.getPgm().compareTo(o2.getPgm()));
					tableViewer.setInput(records);
				}
				else if(StringUtils.equals(combo_filter.getText(), "Gefunden")) {				
					ArrayList<ControlRecord> records = reader.getFoundAndExistingElements();
					records.sort((o1, o2) -> o1.getPgm().compareTo(o2.getPgm()));
					tableViewer.setInput(records);
				}
				else if(StringUtils.equals(combo_filter.getText(), "Nicht gefunden")) {				
					ArrayList<ControlRecord> records = reader.getAllNotFoundElements();
					records.sort((o1, o2) -> o1.getPgm().compareTo(o2.getPgm()));
					tableViewer.setInput(records);
				}
				else if(StringUtils.equals(combo_filter.getText(), "Nicht zugeordnet")){
					ArrayList<ControlRecord> records = reader.getUndefinedElements();
					records.sort((o1, o2) -> o1.getPgm().compareTo(o2.getPgm()));
					tableViewer.setInput(records);
				}
				else {					
				}				
			}
		});
		
		tableColumn_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(StringUtils.equals(combo_filter.getText(), "Alle")) {
					ArrayList<ControlRecord> records = reader.getAllFoundElements();	
					records.sort((o1, o2) -> o1.getSan().compareTo(o2.getSan()));
					tableViewer.setInput(records);
				}
				else if(StringUtils.equals(combo_filter.getText(), "Gefunden")) {				
					ArrayList<ControlRecord> records = reader.getFoundAndExistingElements();
					records.sort((o1, o2) -> o1.getSan().compareTo(o2.getSan()));
					tableViewer.setInput(records);
				}
				else if(StringUtils.equals(combo_filter.getText(), "Nicht gefunden")) {				
					ArrayList<ControlRecord> records = reader.getAllNotFoundElements();
					records.sort((o1, o2) -> o1.getSan().compareTo(o2.getSan()));
					tableViewer.setInput(records);
				}
				else if(StringUtils.equals(combo_filter.getText(), "Nicht zugeordnet")){
					ArrayList<ControlRecord> records = reader.getUndefinedElements();
					records.sort((o1, o2) -> o1.getSan().compareTo(o2.getSan()));
					tableViewer.setInput(records);
				}
				else {					
				}	
			}
		});
		
		tableColumn_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(StringUtils.equals(combo_filter.getText(), "Alle")) {
					ArrayList<ControlRecord> records = reader.getAllFoundElements();	
					records.sort((o1, o2) -> o1.getFld().compareTo(o2.getFld()));
					tableViewer.setInput(records);
				}
				else if(StringUtils.equals(combo_filter.getText(), "Gefunden")) {				
					ArrayList<ControlRecord> records = reader.getFoundAndExistingElements();
					records.sort((o1, o2) -> o1.getFld().compareTo(o2.getFld()));
					tableViewer.setInput(records);
				}
				else if(StringUtils.equals(combo_filter.getText(), "Nicht gefunden")) {				
					ArrayList<ControlRecord> records = reader.getAllNotFoundElements();
					records.sort((o1, o2) -> o1.getFld().compareTo(o2.getFld()));
					tableViewer.setInput(records);
				}
				else if(StringUtils.equals(combo_filter.getText(), "Nicht zugeordnet")){
					ArrayList<ControlRecord> records = reader.getUndefinedElements();
					records.sort((o1, o2) -> o1.getFld().compareTo(o2.getFld()));
					tableViewer.setInput(records);
				}
				else {					
				}	
			}
		});
		
		tableColumn_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(StringUtils.equals(combo_filter.getText(), "Alle")) {
					ArrayList<ControlRecord> records = reader.getAllFoundElements();	
					records.sort((o1, o2) -> o1.getTxt().compareTo(o2.getTxt()));
					tableViewer.setInput(records);
				}
				else if(StringUtils.equals(combo_filter.getText(), "Gefunden")) {				
					ArrayList<ControlRecord> records = reader.getFoundAndExistingElements();
					records.sort((o1, o2) -> o1.getTxt().compareTo(o2.getTxt()));
					tableViewer.setInput(records);
				}
				else if(StringUtils.equals(combo_filter.getText(), "Nicht gefunden")) {				
					ArrayList<ControlRecord> records = reader.getAllNotFoundElements();
					records.sort((o1, o2) -> o1.getTxt().compareTo(o2.getTxt()));
					tableViewer.setInput(records);
				}
				else if(StringUtils.equals(combo_filter.getText(), "Nicht zugeordnet")){
					ArrayList<ControlRecord> records = reader.getUndefinedElements();
					records.sort((o1, o2) -> o1.getTxt().compareTo(o2.getTxt()));
					tableViewer.setInput(records);
				}
				else {					
				}	
			}
		});
		
		ProgressBar progressbar = new ProgressBar(composite, SWT.NONE);
		progressbar.setBounds(172, 127, 795, 10);				
	}	
	

	private ArrayList<ControlRecord> filterElementsAsList(ArrayList<ControlRecord> records){
		ArrayList<ControlRecord> newList  = new ArrayList<>();
		for(ControlRecord c : records) {
			if(
					StringUtils.contains(c.getPgm().toLowerCase(), isTextfieldEmpty(txt_ctrpgm.getText().toLowerCase(), c.getPgm().toLowerCase())) && 
					StringUtils.contains(StringUtils.remove(c.getSan(), ".").toLowerCase(), isTextfieldEmpty(StringUtils.remove(txt_ctrsan.getText(), "."), StringUtils.remove(c.getSan(), ".")).toLowerCase()) && 				
					StringUtils.contains(c.getFld().toLowerCase(), isTextfieldEmpty(txt_ctrfld.getText().toLowerCase(), c.getFld().toLowerCase())) && 
					StringUtils.contains(c.getTxt().toLowerCase(), isTextfieldEmpty(txt_ctrtext.getText().toLowerCase(), c.getTxt().toLowerCase()))) {
				newList.add(c);
			}				
		}	
		return newList;
	}
	
	private String isTextfieldEmpty(String txtField, String txtObject) {
		if(StringUtils.equals(txtField, StringUtils.EMPTY)) {
			return txtObject;
		}
		else {
			return txtField;
		}
	}
	
	public String getfolderDirectory() {
		return folderDirectory;
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
}

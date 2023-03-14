package com.ametras.kontrollsatz;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	@Override	
	public void createInitialLayout(IPageLayout layout) {
		layout.addView("com.ametras.controlrecord.FilesData",
				IPageLayout.LEFT, 0.4f, layout.getEditorArea());
		
		layout.setEditorAreaVisible(false);
	}
}

package com.chineseflashcards;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChineseController {
	ChineseMainView view;
	ChineseModel model;

	// Declare views
	ChineseDataView dataView;

	public ChineseController(ChineseMainView view, ChineseModel model) {
		this.view = view;
		this.model = model;

		// Initialize views
		dataView = new ChineseDataView();

		// Add views to model
		model.addView(dataView);

		// Add views to main view as separate tabs
		view.addTab("Data", dataView);

		// Add action listeners to dataView buttons
		dataView.addButtonListeners(new DataViewButtonsListener());
	}

	private class DataViewButtonsListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String[] data = dataView.getControlPanelData();
			model.addEntry(data);
		}
	}
}

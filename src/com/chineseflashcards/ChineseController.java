package com.chineseflashcards;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

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
		
		// Add table model listener for data view
		// IT IS NOT NEEDED YET (BECAUSE TABLE IS NOT EDITABLE DIRECTLY)
		dataView.addTableModelListener(new DataViewTableModelListener());
		
		// Add selection listener to table in data view
		dataView.addSelectionListener(new DataViewTableSelectionListener());
	}

	// Listens to Add, Remove and Edit buttons in data view
	private class DataViewButtonsListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand() == "Add") {
				String[] data = dataView.getControlPanelData();
				model.addEntry(data);
			} else if (e.getActionCommand() == "Remove") {
				
			}
		}
	}
	
	// Listens to table model in data view
	private class DataViewTableModelListener implements TableModelListener {
		public void tableChanged(TableModelEvent e) {
			System.out.println(e);
		}
	}
	
	// Listens to table selections in data view
	private class DataViewTableSelectionListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e) {
			System.out.println(e);
		}
	}
}

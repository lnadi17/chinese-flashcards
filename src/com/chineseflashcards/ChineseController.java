package com.chineseflashcards;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.DefaultListSelectionModel;
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
		dataView = new ChineseDataView(model.getData());

		// Add views to model
		model.addView(dataView);

		// Add views to main view as separate tabs
		view.addTab("Data", dataView);

		// Add action listeners to dataView buttons
		dataView.addButtonListeners(new DataViewButtonsListener());

		// Add table model listener for data view
		dataView.addTableModelListener(new DataViewTableModelListener());

		// Add selection listener to table in data view
		dataView.addSelectionListener(new DataViewTableSelectionListener());
	}

	// Listens to Add, Remove and Edit buttons in data view
	private class DataViewButtonsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			//System.out.println(e);
		}

	}

	// Listens to table model in data view
	private class DataViewTableModelListener implements TableModelListener {
		@Override
		public void tableChanged(TableModelEvent e) {
			MyTableModel changedModel = (MyTableModel) e.getSource();
			int changedRow = e.getLastRow();
			Object[] row = changedModel.getRow(changedRow);
			String[] subRow = Arrays.copyOfRange(row, 1, row.length, String[].class);
			model.removeEntry(changedRow);
			model.addEntry(subRow, changedRow);
		}
	}

	// Listens to table selections in data view
	private class DataViewTableSelectionListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (e.getValueIsAdjusting() == false) {
				//System.out.println(e);
			}
		}
	}
}

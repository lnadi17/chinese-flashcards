package com.chineseflashcards;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
	ChinesePracticeView practiceView;

	// Declare variables which need tracking
	/* (DATA VIEW VARIABLES) */
	ArrayList<Integer> dataViewSelectedRows = new ArrayList<Integer>();
	DefaultListSelectionModel lastListSelectionModel;

	public ChineseController(ChineseMainView view, ChineseModel model) {
		this.view = view;
		this.model = model;

		// Initialize views
		dataView = new ChineseDataView(model.getData());
		practiceView = new ChinesePracticeView();

		// Add views to model
		model.addView(dataView);
		model.addView(practiceView);

		// Add views to main view as separate tabs
		view.addTab("Data", dataView);
		view.addTab("Practice", practiceView);

		// Add action listeners to dataView buttons
		dataView.addButtonListeners(new DataViewButtonsListener());

		// Add table model listener for data view
		dataView.addTableModelListener(new DataViewTableModelListener());

		// Add selection listener to table in data view
		dataView.addSelectionListener(new DataViewTableSelectionListener());

		// Add action listeners to practiceView buttons
		practiceView.addButtonListeners(new PracticeViewButtonsListener());
	}

	// Listens to Add, Remove and Edit buttons in data view
	private class DataViewButtonsListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand() == "Add") {
				// Adds empty row
				model.addEntry(new String[] { "", "", "" });

			} else if (e.getActionCommand() == "Remove") {
				// If no rows are selected, ask user to select them
				if (dataViewSelectedRows == null) {
					InfoClass.infoBox("Please select at least one row to remove.");
					return;
				}

				// Looping backwards to avoid error
				for (int i = dataViewSelectedRows.size() - 1; i >= 0; i--) {
					// Remove every selected row
					model.removeEntry(dataViewSelectedRows.get(i));
				}

				// Forces user to select rows if they want to use remove button again
				clearRowSelection();
			}
		}
	}

	// Listens to table model in data view
	private class DataViewTableModelListener implements TableModelListener {
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
		public void valueChanged(ListSelectionEvent e) {
			if (e.getValueIsAdjusting() == false) {
				lastListSelectionModel = ((DefaultListSelectionModel) e.getSource());
				System.out.println(lastListSelectionModel);
				dataViewSelectedRows = parsedataViewSelectedRows(lastListSelectionModel.toString());
			}
		}

		// Returns selected row indexes wrapped in an array
		private ArrayList<Integer> parsedataViewSelectedRows(String str) {
			// You're not supposed to get this
			String[] answerStr = str.substring(str.lastIndexOf('{') + 1, str.lastIndexOf('}')).split(", ");

			// Return null if no rows are selected
			if (answerStr[0].equals(""))
				return null;

			return stringArrayToIntArray(answerStr);
		}

		private ArrayList<Integer> stringArrayToIntArray(String[] stringArray) {
			ArrayList<Integer> intArray = new ArrayList<Integer>();

			@SuppressWarnings("unused")
			int i = 0;
			for (String s : stringArray) {
				intArray.add(Integer.parseInt(s));
				i++;
			}
			return intArray;
		}
	}

	private class PracticeViewButtonsListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand() == "Turn") {
				practiceView.turnCurrentCard();
			} else if (e.getActionCommand() == "Next") {
				// Turn card to question side before switching
				practiceView.setQuestionSide();
				practiceView.nextCard();
			} else if (e.getActionCommand() == "Prev") {
				practiceView.setQuestionSide();
				practiceView.prevCard();
			}
		}
	}

	// Clears selected rows
	private void clearRowSelection() {
		dataViewSelectedRows.clear();
		lastListSelectionModel.clearSelection();
	}
}

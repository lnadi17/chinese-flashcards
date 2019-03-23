package com.chineseflashcards;

import java.util.*;
import javax.swing.*;
import java.awt.*;

public class ChineseDataView extends ChineseView {
	// Global variables
	JPanel centerPanel;
	JScrollPane scrollPane;

	ChineseDataView() {
		// This view has grid layout with 1 column and 2 rows
		this.setLayout(new GridLayout(1, 0));
		this.setBorder(BorderFactory.createEmptyBorder(5, 10, 20, 10));
	}

	@Override
	public void createView(Map<Integer, ArrayList<String>> data) {
		// Convert data for table
		Object[] columnArray = getColumnArray();
		Object[][] tableData = getTableData(data);
		// Create panel
		centerPanel = new JPanel(new GridLayout());
		// Create data view
		JTable table = new JTable(tableData, columnArray);
		scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		// Add data view to panel
		centerPanel.add(scrollPane);
		// Add panel on JFrame
		add(centerPanel, BorderLayout.CENTER);
	}

	@Override
	public void clearView() {
		if (centerPanel != null)
			remove(centerPanel);
	}

	// Returns column names (to be changed!)
	private Object[] getColumnArray() {
		return new Object[] { "id", "georgian", "pinyuin", "mandarin" };
	}

	// Returns table data (to be changed!)
	private Object[][] getTableData(Map<Integer, ArrayList<String>> data) {
		Object[][] returnVal = new Object[data.size()][data.get(0).size()];
		for (int k : data.keySet()) {
			Object[] row = new Object[data.get(k).size() + 1];
			row[0] = k;
			row[1] = data.get(k).get(0);
			row[2] = data.get(k).get(1);
			row[3] = data.get(k).get(2);
			returnVal[k] = row;
		}
		return returnVal;
	}

	public void getData() {

	}
}

package com.chineseflashcards;

import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class ChineseDataView extends ChineseView {
	// Global variables
	JPanel centerPanel;
	JScrollPane scrollPane;

	ChineseDataView() {

	}

	@Override
	public void createView(Map<Integer, ArrayList<String>> data) {
		// Convert data for table
		Object[] columnArray = getColumnArray();
		Object[][] tableData = getTableData(data);
		// Create panel
		centerPanel = new JPanel();
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
		return new Object[] {"id", "georgian", "pinyuin", "mandarin"};
	}
	
	// Returns table data
	private Object[][] getTableData(Map<Integer, ArrayList<String>> data) {
		return new Object[][] {getColumnArray(), getColumnArray()};
	}

	public void getData() {
		
	}
}

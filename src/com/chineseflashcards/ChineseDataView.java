package com.chineseflashcards;

import java.util.*;
import javax.swing.*;

import org.json.simple.JSONObject;

import java.awt.*;

public class ChineseDataView extends ChineseView {
	// Global variables
	JPanel dataPanel;
	JPanel controlPanel;
	
	JScrollPane scrollPane;

	ChineseDataView() {
		// This view has grid layout with 1 column and 2 rows
		this.setLayout(new GridLayout(1, 0));
		this.setBorder(BorderFactory.createEmptyBorder(5, 10, 20, 10));
	}

	@Override
	public void createView(JSONObject data) {
		// Create panel
		dataPanel = new JPanel(new GridLayout());
		
		// Create data view
		JTable table = new JTable(new MyTableModel(data)); // Check if it needs to recreate table every time (TODO)
		scrollPane = new JScrollPane(table);

		// Add data view to panel
		dataPanel.add(scrollPane);

		// Add panel on JFrame
		add(dataPanel, BorderLayout.CENTER);
	}

	@Override
	public void clearView() {
		if (dataPanel != null)
			remove(dataPanel);
	}

	public void getData() {

	}
}

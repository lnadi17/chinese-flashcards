package com.chineseflashcards;

import javax.swing.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.awt.*;

public class ChineseDataView extends ChineseView {
	// Global variables
	JPanel dataPanel;
	JPanel controlPanel;
	
	JTextField field1;
	JTextField field2;
	JTextField field3;
	
	JLabel fieldLabel1;
	JLabel fieldLabel2;
	JLabel fieldLabel3;
	
	JButton addButton;
	JButton removeButton;
	JButton editButton;
	
	JScrollPane scrollPane;

	ChineseDataView() {
		// This view has grid layout with 1 column and 2 rows
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
		
		// Initialize JTextFields and JButtons
		field1 = new JTextField(20);
		field2 = new JTextField(20);
		field3 = new JTextField(20);
		
		addButton = new JButton("Add");
		removeButton = new JButton("Edit");
		editButton = new JButton("Remove");
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

		// Add data panel to ChineseView (outermost JPanel)
		add(dataPanel, BorderLayout.CENTER);
		
		// Create control panel
		controlPanel = new JPanel();
		
		// Choose control panel layout
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.LINE_AXIS));
		
		// Set field labels using data
		setFieldLabels(data);
		
		// Add components to control panel
		controlPanel.add(fieldLabel1);
		controlPanel.add(field1);
		controlPanel.add(fieldLabel2);
		controlPanel.add(field2);
		controlPanel.add(fieldLabel3);
		controlPanel.add(field3);

		controlPanel.add(addButton);
		controlPanel.add(editButton);
		controlPanel.add(removeButton);
		
		// Add control panel to ChineseView (outermost JPanel)
		controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		add(controlPanel, BorderLayout.SOUTH);
	}

	private void setFieldLabels(JSONObject data) {
		// Extract label names from data
		JSONArray labels = (JSONArray) data.get("columnNames");
		fieldLabel1 = new JLabel((String) labels.get(1));
		fieldLabel2 = new JLabel((String) labels.get(2));
		fieldLabel3 = new JLabel((String) labels.get(3));	
	}

	@Override
	public void clearView() {
		if (dataPanel != null)
			remove(dataPanel);
	}

	public void getControlPanelData() {

	}
}

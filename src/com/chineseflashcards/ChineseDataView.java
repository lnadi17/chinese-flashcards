package com.chineseflashcards;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelListener;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.awt.*;
import java.awt.event.ActionListener;

public class ChineseDataView extends ChineseView {
	// Global variables
	JPanel dataPanel;
	JPanel controlPanel;

	JTable table;
	MyTableModel tableModel;
	
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

		// Initialize table (only table model is going to change, not JTable itself)
		table = new JTable();
		// User can select only one at a time, at this time
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
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
		// Create data panel
		dataPanel = new JPanel(new GridLayout());

		// Create data view
		tableModel = new MyTableModel(data);
		table.setModel(tableModel);
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
		fieldLabel1.setLabelFor(field1);
		fieldLabel2.setLabelFor(field2);
		fieldLabel2.setLabelFor(field3);
	}

	@Override
	public void clearView() {
		removeAll();
	}

	public String[] getControlPanelData() {
		// Array has the same length as there are JTextFields
		String[] cpData = new String[3];
		cpData[0] = field1.getText();
		cpData[1] = field2.getText();
		cpData[2] = field3.getText();
		return cpData;
	}

	public void addButtonListeners(ActionListener a) {
		addButton.addActionListener(a);
		editButton.addActionListener(a);
		removeButton.addActionListener(a);
	}
	
	public void addTableModelListener(TableModelListener a) {
		tableModel.addTableModelListener(a);
	}
		
	public void addSelectionListener(ListSelectionListener a) {
		table.getSelectionModel().addListSelectionListener(a);
	}
}

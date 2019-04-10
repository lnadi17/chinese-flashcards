package com.chineseflashcards;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelListener;

import org.json.simple.JSONObject;
import java.awt.*;
import java.awt.event.ActionListener;

public class ChineseDataView extends ChineseView {
	// Global variables
	JPanel dataPanel;
	JPanel controlPanel;

	JTable table;
	MyTableModel tableModel;

	JButton addButton;
	JButton removeButton;

	JScrollPane scrollPane;

	ChineseDataView(JSONObject data) {
		// This view has border layout
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));

		// Initialize table (only table model is going to change, not the JTable itself)
		table = new JTable();
		tableModel = new MyTableModel(data);
		table.setModel(tableModel);
		table.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		addButton = new JButton("Add");
		removeButton = new JButton("Remove");
	}

	@Override
	public void createView(JSONObject data) {
		// Create data panel
		dataPanel = new JPanel(new GridLayout());

		// Create data view and update table model
		tableModel.update(data);
		scrollPane = new JScrollPane(table);

		// Add data view to panel
		dataPanel.add(scrollPane);

		// Add data panel to ChineseView (outermost JPanel)
		add(dataPanel, BorderLayout.CENTER);

		// Create control panel
		controlPanel = new JPanel();

		// Choose control panel layout
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.LINE_AXIS));

		controlPanel.add(addButton);
		controlPanel.add(removeButton);

		// Add control panel to ChineseView (outermost JPanel)
		controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		add(controlPanel, BorderLayout.SOUTH);
	}

	@Override
	public void clearView() {
		removeAll();
	}

	public void addButtonListeners(ActionListener a) {
		addButton.addActionListener(a);
		removeButton.addActionListener(a);
	}

	public void addTableModelListener(TableModelListener a) {
		tableModel.addTableModelListener(a);
	}

	public void addSelectionListener(ListSelectionListener a) {
		table.getSelectionModel().addListSelectionListener(a);
	}
}

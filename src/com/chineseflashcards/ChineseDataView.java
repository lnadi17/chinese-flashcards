package com.chineseflashcards;

import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class ChineseDataView extends ChineseView {
	ChineseDataView() {

		createSouthPanel(lab1, lab2, lab3);
	}

	@Override
	public void createView(Map<Integer, ArrayList<String>> data) {
		// Create panel
		centerPanel = new JPanel();
		// Create data view
		JLabel dataLabel = new JLabel(data.toString());
		// Add data view to panel
		centerPanel.add(dataLabel);
		// Add panel on JFrame
		add(centerPanel, BorderLayout.CENTER);
	}

	@Override
	public void clearView() {
		data1.setText("");
		data2.setText("");
		data3.setText("");
		if (centerPanel != null)
			remove(centerPanel);
	}

	private Border getBorder(int top, int bottom, int left, int right) {
		return BorderFactory.createEmptyBorder(top, left, bottom, right);
	}

	private void createSouthPanel(JLabel lab1, JLabel lab2, JLabel lab3) {
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.PAGE_AXIS));
		southPanel.setBorder(getBorder(0, 10, 0, 0));

		JPanel textFieldPanel = new JPanel();
		textFieldPanel.setLayout(new BoxLayout(textFieldPanel, BoxLayout.LINE_AXIS));

		textFieldPanel.add(Box.createRigidArea(new Dimension(15, 0)));
		textFieldPanel.add(lab1);
		textFieldPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		textFieldPanel.add(data1);

		textFieldPanel.add(Box.createRigidArea(new Dimension(15, 0)));
		textFieldPanel.add(lab2);
		textFieldPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		textFieldPanel.add(data2);

		textFieldPanel.add(Box.createRigidArea(new Dimension(15, 0)));
		textFieldPanel.add(lab3);
		textFieldPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		textFieldPanel.add(data3);

		textFieldPanel.add(Box.createRigidArea(new Dimension(15, 0)));
		textFieldPanel.add(button1);
		textFieldPanel.add(Box.createRigidArea(new Dimension(15, 0)));

		southPanel.add(textFieldPanel);
		add(southPanel, BorderLayout.SOUTH);
	}

	public String getData1() {
		return data1.getText();
	}

	public String getData2() {
		return data2.getText();
	}

	public String getData3() {
		return data3.getText();
	}

	public void addDataViewListener(ActionListener a) {
		button1.addActionListener(a);
	}
}

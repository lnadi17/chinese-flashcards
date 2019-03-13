package com.chineseflashcards;

import javax.swing.*;

public abstract class ChineseView extends JFrame {
	ChineseView() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//this.pack();
		this.setSize(200, 200);
		this.setVisible(true);
	}

	// We still don't know what type of data we'll use
	public abstract void createView(String[][] data);

	public void update(ChineseModel model) {
		// Remove previous data here

		// Create view using data
		createView(model.getData());
	}
}

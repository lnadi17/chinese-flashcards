package com.chineseflashcards;
import java.util.*;
import javax.swing.*;

public abstract class ChineseView extends JFrame {
	ChineseView() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//this.pack();
		this.setSize(500, 500);
		this.setVisible(true);
	}

	// We still don't know what type of data we'll use
	public abstract void createView(Map<Integer, ArrayList<String>> data);
	public abstract void clearView();

	public void update(ChineseModel model) {
		// Remove previous data here
		clearView();
		// Create view using data
		createView(model.getData());
		// 
		validate();
	}
}

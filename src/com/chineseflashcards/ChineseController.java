package com.chineseflashcards;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChineseController {
	public ChineseMainView view;
	public ChineseModel model;

	public ChineseController(ChineseMainView view, ChineseModel model) {
		this.view = view;
		this.model = model;
		
		// Create views
		ChineseDataView dataView = new ChineseDataView();
		
		// Add views to model
		model.addView(dataView);
		//view.add(dataView);
		
		// Add views on main view as separate tabs
		view.addTab("Data", dataView);
	}
}

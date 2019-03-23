package com.chineseflashcards;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class ChineseMainView extends JFrame {
	JTabbedPane tabbedPane = new JTabbedPane();
	
	public ChineseMainView() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//this.pack();
		this.setSize(500, 500);
		this.setVisible(true);
		this.add(tabbedPane);
	}
	
	public void addTab(String title, JPanel panel) {
		tabbedPane.addTab(title, panel);
	}
}

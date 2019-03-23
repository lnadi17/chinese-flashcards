package com.chineseflashcards;

import javax.swing.JFrame;

public class Chinese {
	public static void main(String[] args) {
		ChineseMainView view = new ChineseMainView();
		ChineseModel model = new ChineseModel();
		ChineseController controller = new ChineseController(view, model);
	}
}

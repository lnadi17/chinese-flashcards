package com.chineseflashcards;

public class Chinese {
	public static void main(String[] args) {
		ChineseMainView view = new ChineseMainView();
		ChineseModel model = new ChineseModel();
		new ChineseController(view, model);
	}
}

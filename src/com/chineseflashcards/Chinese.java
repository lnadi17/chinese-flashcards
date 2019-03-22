package com.chineseflashcards;

public class Chinese {
	public static void main(String[] args) {
		ChineseDataView view = new ChineseDataView();
		ChineseModel model = new ChineseModel();
		ChineseController controller = new ChineseController(view, model);
	}
}

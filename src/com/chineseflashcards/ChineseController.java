package com.chineseflashcards;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ChineseController {
	public ChineseDataView view;
	public ChineseModel model;

	public ChineseController(ChineseDataView view, ChineseModel model) {
		this.view = view;
		this.model = model;

		model.addView(view);

		DataViewButtonListener l = new DataViewButtonListener();
		view.addDataViewListener(l);
	}

	private class DataViewButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			ArrayList<String> list = new ArrayList<String>();
			list.add(view.getData1());
			list.add(view.getData2());
			list.add(view.getData3());
			model.addEntry(model.getData().size(), list);
			System.out.println(model.getData().toString());
		}
	}
}

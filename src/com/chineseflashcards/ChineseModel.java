package com.chineseflashcards;

import java.util.*;

public class ChineseModel {

	private ArrayList<ChineseView> views;

	ChineseModel() {
		views = new ArrayList<ChineseView>();
		loadData();
	}

	public void addView(ChineseView view) {
		views.add(view);
	}

	public void addEntry() {
		// Add entry to data

		notifyViews();
	}

	public void removeEntry() {
		// Remove entry from data

		notifyViews();
	}

	private void notifyViews() {
		// Call each view's update
		for (ChineseView v : views) {
			v.update(this);
		}
	}

	// We still don't know which data type we'll use
	public String[][] getData() {
		return null;
	}

	private void loadData() {
		// Read saved data from file or files and store it in some variable

	}
}

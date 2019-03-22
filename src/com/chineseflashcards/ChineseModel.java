package com.chineseflashcards;

import java.util.*;

public class ChineseModel {
	private ArrayList<ChineseView> views;
	private Map<Integer, ArrayList<String>> data = new HashMap<Integer, ArrayList<String>>();

	ChineseModel() {
		views = new ArrayList<ChineseView>();
		loadData();
	}

	public void addView(ChineseView view) {
		views.add(view);
		// We don't if we should call that method from here yet
		notifyViews();
	}

	public void addEntry(int id, ArrayList<String> arrayList) {
		// Add entry to data
		data.put(id, arrayList);
		notifyViews();
	}

	public void removeEntry(int id) {
		// Remove entry from data
		data.remove(id);
		notifyViews();
	}

	private void notifyViews() {
		// Call each view's update
		for (ChineseView v : views) {
			v.update(this);
		}
	}

	// We still don't know which data type we'll use
	public Map<Integer, ArrayList<String>> getData() {
		return data;
	}

	private void loadData() {
		// Read saved data from file or files and store it in some variable
		ArrayList<String> oe = new ArrayList<String>(Arrays.asList("ჩინური ენა", "ha4nyu3", "汉语"));
		data.put(0, oe);
	}
}

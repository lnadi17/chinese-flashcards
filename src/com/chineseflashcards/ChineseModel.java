package com.chineseflashcards;

import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ChineseModel {
	private ArrayList<ChineseView> views;
	private JSONObject data = new JSONObject();

	ChineseModel() {
		views = new ArrayList<ChineseView>();
		loadData();
	}

	public void addView(ChineseView view) {
		views.add(view);
		// We don't if we should call that method from here yet
		notifyViews();
	}

	@SuppressWarnings("unchecked")
	public void addEntry(int id, JSONArray array) {
		// Add entry to word data
		JSONArray arr = (JSONArray) data.get("entries");
		arr.add(id, array);
		System.out.println(data.get("entries"));
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
	public JSONObject getData() {
		return data;
	}

	private void loadData() {
		JSONParser parser = new JSONParser();
		String s = "{\"columnNames\":[\"id\",\"meaning\",\"pinyin\",\"mandarin\"],\"entries\":[[\"ჩინური ენა\",\"ha4nyu3\",\"汉语\"],[\"ლამაზი, კარგი შესახედი\",\"ha3oka4n\",\"好看\"],[\"მეგობარი\",\"pe2ngyou\",\"朋友\"]]}";
		try {
			data = (JSONObject) parser.parse(s);
			System.out.println(data.toString());
		} catch (ParseException pe) {
			System.out.println("position: " + pe.getPosition());
			System.out.println(pe);
		}
	}
}

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
	// Add entry to data
	public void addEntry(String[] array) {
		JSONArray passedArray = arrayToJSONArray(array);
		JSONArray dataArray = (JSONArray) data.get("entries");
		// Adds converted passed array to data array
		dataArray.add(getLastID() + 1, passedArray);
		notifyViews();
	}

	// Not yet implemented
	public void removeEntry(int id) {
		// Remove entry from data

		notifyViews();
	}

	private void notifyViews() {
		// Call each view's update
		for (ChineseView v : views) {
			v.update(this);
		}
	}

	public JSONObject getData() {
		return data;
	}

	private void loadData() {
		JSONParser parser = new JSONParser();
		String s = "{\"columnNames\":[\"ID\",\"Meaning\",\"Pinyin\",\"Mandarin\"],\"entries\":[[\"ჩინური ენა\",\"ha4nyu3\",\"汉语\"],[\"ლამაზი, კარგი შესახედი\",\"ha3oka4n\",\"好看\"],[\"მეგობარი\",\"pe2ngyou\",\"朋友\"]]}";
		try {
			data = (JSONObject) parser.parse(s);
			// System.out.println(data.toString());
		} catch (ParseException pe) {
			System.out.println("position: " + pe.getPosition());
			System.out.println(pe);
		}
	}

	@SuppressWarnings("unchecked")
	private JSONArray arrayToJSONArray(String[] stringArray) {
		JSONArray result = new JSONArray();
		for (int i = 0; i < stringArray.length; i++) {
			result.add(stringArray[i]);
		}
		return result;
	}
	
	// Can be optimized
	private int getLastID() {
		return ((JSONArray) data.get("entries")).size() - 1;
	}
}

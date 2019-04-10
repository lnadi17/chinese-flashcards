package com.chineseflashcards;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ChineseModel {
	public static String RES_PATH = "res/";
	private ArrayList<ChineseView> views;
	private JSONObject data = new JSONObject();

	ChineseModel() {
		views = new ArrayList<ChineseView>();
		loadData();
	}

	public void addView(ChineseView view) {
		views.add(view);
		notifyViews();
	}

	@SuppressWarnings("unchecked")
	// Add entry to data
	public void addEntry(Object[] array, int id) {
		JSONArray passedArray = arrayToJSONArray(array);
		JSONArray dataArray = (JSONArray) data.get("entries");
		// Adds converted passed array to data array
		dataArray.add(id, passedArray);
		saveDataToFile(RES_PATH + "data.json");
		notifyViews();
	}
	
	@SuppressWarnings("unchecked")
	public void addEntry(Object[] array) {
		JSONArray passedArray = arrayToJSONArray(array);
		JSONArray dataArray = (JSONArray) data.get("entries");
		// Adds converted passed array to data array
		dataArray.add(getLastID() + 1, passedArray);
		saveDataToFile(RES_PATH + "data.json");
		notifyViews();
	}

	public void removeEntry(int id) {
		// Remove entry from data
		((JSONArray) data.get("entries")).remove(id);
		saveDataToFile(RES_PATH + "data.json");
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
		String s = readFromDataFile(RES_PATH + "data.json");
		try {
			data = (JSONObject) parser.parse(s);
		} catch (ParseException pe) {
			System.out.println("position: " + pe.getPosition());
			System.out.println(pe);
		}
	}

	@SuppressWarnings("unchecked")
	private JSONArray arrayToJSONArray(Object[] stringArray) {
		JSONArray result = new JSONArray();
		for (int i = 0; i < stringArray.length; i++) {
			result.add(stringArray[i]);
		}
		return result;
	}

	// You can always change an implementation, right?
	private String readFromDataFile(String fileName) {
		String result = null;

		try {
			FileInputStream fis = new FileInputStream(fileName);
			DataInputStream dis = new DataInputStream(fis);
			result = dis.readUTF();
			dis.close();
		} catch (FileNotFoundException e) {
			System.out.println("Data file not found.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error reading from the data file.");
			e.printStackTrace();
		}

		return result;
	}

	private void saveDataToFile(String fileName) {
		try {
			FileOutputStream fos = new FileOutputStream(fileName);
			DataOutputStream dos = new DataOutputStream(fos);
			dos.writeUTF(data.toString());
			dos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private int getLastID() {
		return ((JSONArray) data.get("entries")).size() - 1;
	}
}

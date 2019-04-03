package com.chineseflashcards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.AbstractTableModel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class MyTableModel extends AbstractTableModel {
	private String[] columnNames;
	private Object[][] data;

	public MyTableModel(JSONObject data) {
		this.columnNames = new String[] { "geo", "pin", "man" };
		this.data = getTableData(data);
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return data.length;
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

	// Returns table data (to be changed!)
	private Object[][] getTableData(JSONObject data) {
		Object[][] res = new Object[data.size()][columnNames.length];
		for (int i = 0; i < data.size(); i++) {
			JSONArray jsonArray = (JSONArray) data.get(Integer.toString(i));
			res[i] = getObjectArray(jsonArray);
		}
		return res;
	}
	
	private Object[] getObjectArray(JSONArray jsonArray) {
		Object[] res = new Object[columnNames.length];
		for (int i = 0; i < res.length; i++) { 
			res[i] = (Object) jsonArray.get(i);
		}
		return res;
	}
}

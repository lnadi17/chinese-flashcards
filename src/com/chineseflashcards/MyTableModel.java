package com.chineseflashcards;

import javax.swing.table.AbstractTableModel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class MyTableModel extends AbstractTableModel {
	private String[] columnNames;
	private Object[][] data;

	public MyTableModel(JSONObject data) {
		this.columnNames = getColumnNameArray(data);
		this.data = convertData(data);
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

	// Basically copies entries (2D JSONArray) to another array and returns it
	private Object[][] convertData(JSONObject data) {
		// Extract entries
		JSONArray entries = (JSONArray) data.get("entries");
		Object[][] res = new Object[entries.size()][columnNames.length];
		
		for (int i = 0; i < entries.size(); i++) {
			Object[] row = new Object[columnNames.length];
			row[0] = i;
			for (int j = 1; j < row.length; j++) {
				row[j] = ((JSONArray) entries.get(i)).get(j - 1);
			}
			res[i] = row;
		}
		return res;
	}

	private String[] getColumnNameArray(JSONObject data) {
		// Extract column names
		JSONArray jsonArray = (JSONArray) data.get("columnNames");
		String[] returnVal = new String[jsonArray.size()];
		for (int i = 0; i < returnVal.length; i++) {
			returnVal[i] = (String) jsonArray.get(i);
		}
		return returnVal;
	}
}

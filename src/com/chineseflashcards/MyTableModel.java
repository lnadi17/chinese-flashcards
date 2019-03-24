package com.chineseflashcards;

import java.util.ArrayList;
import java.util.Map;
import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel {
	private String[] columnNames;
	private Object[][] data;

	public MyTableModel(Map<Integer, ArrayList<String>> data) {
		this.columnNames = new String[] { "id", "geo", "pin", "man" };
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
	private Object[][] getTableData(Map<Integer, ArrayList<String>> data) {
		Object[][] returnVal = new Object[data.size()][data.get(0).size()];
		for (int k : data.keySet()) {
			Object[] row = new Object[data.get(k).size() + 1];
			row[0] = k;
			row[1] = data.get(k).get(0);
			row[2] = data.get(k).get(1);
			row[3] = data.get(k).get(2);
			returnVal[k] = row;
		}
		return returnVal;
	}
}

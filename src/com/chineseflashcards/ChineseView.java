package com.chineseflashcards;
import java.util.*;
import javax.swing.*;

import org.json.simple.JSONObject;

public abstract class ChineseView extends JPanel {
	ChineseView() {
		
	}

	// We still don't know what type of data we'll use
	public abstract void createView(JSONObject data);
	public abstract void clearView();

	public void update(ChineseModel model) {
		// Remove previous data here
		clearView();
		// Create view using data
		createView(model.getData());
		// 
		validate();
	}
}

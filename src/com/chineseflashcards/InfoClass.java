package com.chineseflashcards;

import javax.swing.JOptionPane;

public class InfoClass {
	public static void infoBox(String infoMessage) {
		JOptionPane.showMessageDialog(null, infoMessage, "Information Box", JOptionPane.INFORMATION_MESSAGE);
	}
}
package com.chineseflashcards;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/* This class saves passed data as a global variable, it doesn't change data, only uses it */
public class ChinesePracticeView extends ChineseView {

	// Saving data in ArrayList will be much easier, but at this moment there seems
	// no consensus on which data structures to use
	ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();

	JPanel flashCards;
	CardLayout cardLayout;

	JButton turnButton;
	JButton nextButton;
	JButton prevButton;

	int currentCardIndex;

	public ChinesePracticeView() {
		this.setLayout(new BorderLayout());
		turnButton = new JButton("Turn");
		nextButton = new JButton("Next");
		prevButton = new JButton("Prev");
	}

	@Override
	public void createView(JSONObject data) {
		saveDataInArrayList(data);
		cardLayout = new CardLayout();
		flashCards = new JPanel(cardLayout);

		add(turnButton, BorderLayout.SOUTH);
		add(prevButton, BorderLayout.WEST);
		add(nextButton, BorderLayout.EAST);

		// These arrays won't be set manually in future, and their sizes must be
		// at least one
		int[] questionIndexes = new int[] { 0 };
		int[] answerIndexes = new int[] { 1, 2 };

		fillFlashCards(questionIndexes, answerIndexes);
		add(flashCards, BorderLayout.CENTER);
		System.out.println(currentCardIndex);
	}

	public void clearView() {
		currentCardIndex = 0;
		data.clear();
		removeAll();
	}

	public void nextCard() {
		cardLayout.next(flashCards);
		currentCardIndex = (currentCardIndex + 1) % data.size();
	}

	public void prevCard() {
		cardLayout.previous(flashCards);
		currentCardIndex = (currentCardIndex - 1 + data.size()) % data.size();
	}

	public void toggleturnButton() {
		if (turnButton.getText().equals("Reveal")) {
			turnButton.setText("Unreveal");
		} else {
			turnButton.setText("Reveal");
		}
	}

	// Following methods have much in common,
	// but separating them is still reasonable
	public void turnCurrentCard() {
		// It peeks inside flashcards, gets single card and shows it's other side
		// (it only has two sides)
		((CardLayout) ((JPanel) flashCards.getComponents()[currentCardIndex]).getLayout())
				.next((Container) flashCards.getComponents()[currentCardIndex]);
	}

	public void setQuestionSide() {
		((CardLayout) ((JPanel) flashCards.getComponents()[currentCardIndex]).getLayout())
				.show((Container) flashCards.getComponents()[currentCardIndex], "q");
	}

	public void setAnswerSide() {
		((CardLayout) ((JPanel) flashCards.getComponents()[currentCardIndex]).getLayout())
				.show((Container) flashCards.getComponents()[currentCardIndex], "a");
	}

	public void addButtonListeners(ActionListener a) {
		turnButton.addActionListener(a);
		prevButton.addActionListener(a);
		nextButton.addActionListener(a);
	}

	private void saveDataInArrayList(JSONObject data) {
		JSONArray jArr = (JSONArray) data.get("entries");
		for (int i = 0; i < jArr.size(); i++) {
			JSONArray jRow = (JSONArray) jArr.get(i);
			ArrayList<String> aRow = new ArrayList<String>();

			for (int j = 0; j < jRow.size(); j++) {

				// Convert to pinyin here

				aRow.add((String) jRow.get(j));
			}

			// Here should be a checkpoint where it skips if the row is empty

			this.data.add(aRow);
		}
		// System.out.println(this.data);
	}

	private void fillFlashCards(int[] questionIndexes, int[] answerIndexes) {
		for (ArrayList<String> row : data) {
			String questionString = "<html><center>";
			for (int q : questionIndexes) {
				questionString += row.get(q) + "<br>";
			}
			questionString += "<center></html>";

			String answerString = "<html><center>";
			for (int a : answerIndexes) {
				answerString += row.get(a) + "<br>";
			}
			answerString += "</center></html>";

			JLabel question = new JLabel(questionString, SwingConstants.CENTER);
			JLabel answer = new JLabel(answerString, SwingConstants.CENTER);
			question.setFont(new Font("Arial", Font.PLAIN, 40));
			answer.setFont(new Font("Sylfaen", Font.PLAIN, 60));

			JPanel singleCard = new JPanel(new CardLayout());
			singleCard.add(question, "q");
			singleCard.add(answer, "a");

			flashCards.add(singleCard);
		}
	}
}

package bots;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class NosyBot {
	private ArrayList<String> questionList = new ArrayList<String>();
	private ArrayList<String> answerList = new ArrayList<String>();
	private ArrayList<String> colorList = new ArrayList<String>();
	private ArrayList<String> foodList = new ArrayList<String>();
	private ArrayList<String> bandList = new ArrayList<String>();
	private ArrayList<String> tvList = new ArrayList<String>();
	private ArrayList<String> movieList = new ArrayList<String>();
	private ArrayList<String> songList = new ArrayList<String>();
	private int random;
	private ArrayList<Integer> previousRandoms = new ArrayList<Integer>();

	public String processInput(String input) {
		if (input == null) {
			importData(
					"BotQuestions.txt",
					questionList);
			importData(
					"Answers.txt",
					answerList);
			importData(
					"Colors.txt",
					colorList);
			importData(
					"Foods.txt",
					foodList);
			importData(
					"Bands.txt",
					bandList);
			importData(
					"TV Shows.txt",
					tvList);
			importData(
					"Movies.txt",
					movieList);
			importData(
					"Songs.txt",
					songList);	
			random = (int) Math
					.round(Math.random() * (questionList.size() - 1));
			return questionList.get(random);
		} else {
			previousRandoms.add(random);
			String answer = "Cool! " + answerList.get(random) + " ";
			switch (random) {
			case 0:
				answer += foodList.get((int) Math.round(Math.random()
						* (foodList.size() - 1)));
				break;
			case 1:
				answer += bandList.get((int) Math.round(Math.random()
						* (bandList.size() - 1)));
				break;
			case 2:
				answer += colorList.get((int) Math.round(Math.random()
						* (colorList.size() - 1)));
				break;
			case 3:
				answer += movieList.get((int) Math.round(Math.random()
						* (movieList.size() - 1)));
				break;
			case 4:
				answer += tvList.get((int) Math.round(Math.random()
						* (tvList.size() - 1)));
				break;
			case 5:
				answer += "NosyBot";
				break;
			case 6: 
				answer += "synecdoche";
				break;
			case 7:
				answer += songList.get((int) Math.round(Math.random()
						* (songList.size() - 1)));
				break;
			}
			if (previousRandoms.size() == questionList.size()) {
				return answer + ". " + "Well, it was nice meeting you! Bye!";
			}
			while (previousRandoms.contains(random)) {
				random = (int) Math.round(Math.random()
						* (questionList.size() - 1));
			}
			if (previousRandoms.size() == questionList.size()) {
				return answer + ". " + "Well, it was nice meeting you! Bye!";
			}
			return answer + ". " + questionList.get(random);

		}
	}

	private ArrayList<String> importData(String filename, ArrayList<String> list) {
		try (BufferedReader br = new BufferedReader(new FileReader(new File(
				filename)))) {
			for (String line; (line = br.readLine()) != null;) {
				list.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
}

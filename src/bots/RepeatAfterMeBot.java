package bots;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RepeatAfterMeBot {
	private boolean isSecondTime;
	private ArrayList<String> ramList = new ArrayList<String>();
	private String currentRepeatWord;
	
	public String processInput(String input) {
		String output = "";
		if (input == null) {
			try (BufferedReader br = new BufferedReader(new FileReader(
					new File("Random Words.txt")))) {
				for (String line; (line = br.readLine()) != null;) {
					ramList.add(line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			isSecondTime = true;
			return "Repeat after me!";
		} else if (isSecondTime) {
			isSecondTime = false;
			if (input.equals("Repeat after me!")) {
				int random = (int) Math.round(Math.random() * 98);
			currentRepeatWord = ramList.get(random);
				return currentRepeatWord;
			} else {
				return "Wrong! Bye!";
			}
		} else {
			if (input.equals(currentRepeatWord)) {
				int random = (int) Math.round(Math.random() * 98);
			currentRepeatWord = ramList.get(random);
				return currentRepeatWord;
			} else {
				return "Wrong! Bye!";
			}
		}

	}
}

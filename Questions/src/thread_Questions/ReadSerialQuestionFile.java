package thread_Questions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ReadSerialQuestionFile {
	private Scanner input;

	private void openFile() {
		try {
			input = new Scanner(new File("questions.txt"));
		} catch (FileNotFoundException fileNotFoundException) {
			System.err.println("Error opening file.");
			System.exit(1);
		}
	}

	public List<Question_Answer> readRecords() {
		List<Question_Answer> questionsList = new ArrayList<Question_Answer>();
		try {
			openFile();
			do {
				String result = input.nextLine();
				ArrayList<String> finalString = new ArrayList<String>();
				int questionStart = result.indexOf("\"");
				int questionEnd = result.indexOf("\"", questionStart + 1);
				while (questionEnd > 0) {
					String question = result.substring(questionStart + 1,
							questionEnd);
					if (!question.isEmpty()) {
						finalString.add(question);
					}
					questionStart = result.indexOf("\"", questionStart + 1);
					questionEnd = result.indexOf("\"", questionEnd + 1);
				}
				String question = finalString.get(0);
				String first = finalString.get(1);
				String second = finalString.get(2);
				String third = finalString.get(3);
				String forth = finalString.get(4);
				int answer = Integer.valueOf(finalString.get(5));
				Question_Answer quest = new Question_Answer(question, first, second, third, forth, answer);
				questionsList.add(quest);
			} while (input.hasNext());
		} catch (NoSuchElementException elementException) {
			System.err.println("File improperly formed.");
			input.close();
			System.exit(1);
		} catch (IllegalStateException stateException) {
			System.err.println("Error reading from file.");
			System.exit(1);
		} finally {
			closeFile();
		}

		return questionsList;
	}

	private void closeFile() {
		if (input != null) {
			input.close();
		}
	}
}

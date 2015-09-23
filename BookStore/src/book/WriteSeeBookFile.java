package book;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class WriteSeeBookFile {
	private ObjectOutputStream output;

	private void openFile() {
		try {
			output = new ObjectOutputStream(new FileOutputStream("subjects.ser"));
		} catch (IOException ioException) {
			System.err.println("Error opening file!!!");
		}
	}

	public void addRecords(HashMap<String, Book> bookMap) {
			openFile();
			try {
				output.writeObject(bookMap);
			} catch (IOException ioException) {
				System.err.println("Error writing to file.");
				return;
			} catch (NoSuchElementException elementException) {
				System.err.println("Invalid input. Please try again.");
			}finally{
				closeFile();
			}
			System.out.println("See you soon!!!");
	}

	private void closeFile() {
		try {
			if (output != null)
				output.close();
		} catch (IOException ioException) {
			System.err.println("Error closing file!!!");
			System.exit(1);
		}
	}
}

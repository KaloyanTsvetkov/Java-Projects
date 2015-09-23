package book;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class ReadSerFileBook {

	private ObjectInputStream input;

	private void openFile(){
		 try {
			input = new ObjectInputStream(new FileInputStream("subjects.ser"));
		} catch (FileNotFoundException e) {
			System.err.println("FIle not found!");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error opening file!");
			e.printStackTrace();
		}
	}
		
		
	public HashMap<String, Book> readFromXMLFile()throws IOException{
		HashMap<String, Book> bookMap = new HashMap<String, Book>();
		  boolean flag = true;
		  
	    try {
	    	openFile();
		      while (flag) {
		    	bookMap = (HashMap<String, Book>) input.readObject();
		      }
		      
		    }catch (EOFException endOfFileException) {
				flag = false;
		    }catch (ClassNotFoundException classNotFoundException) {
		      System.err.println("Unable to create object.");
		    } catch (IOException ioException) {
			      System.err.println("Error during read from file.");
			} finally{
				closeFile();
			}
		return bookMap;	
	}

	private void closeFile() {
		try {
			if (input != null)
				input.close();
		} catch (IOException ioException) {
			System.err.println("Error closing file.");
			System.exit(1);
		}
	}
}
	
	
	

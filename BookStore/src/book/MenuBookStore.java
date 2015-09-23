package book;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class MenuBookStore {
	private HashMap<String, Book> bookMap;
	
	public MenuBookStore() {
		this.bookMap = new HashMap<String, Book>();
	}

	public void menuOptions(){
		Scanner input2 = new Scanner(System.in);
		boolean exit = true;
		int options;
		readSerFile();
		do {
			if (getBookMap().isEmpty()) {
				System.err.println("You dont`t have an any books in your store...");
				setBookMap(BookMethods.loadingBookStore(setFileName()));
			} else {
				do {
					System.out.println("\nPlease choose one option..."
							+ "\n\t1. To see all books in the store."
							+ "\n\t2. To increase book's price with 20%."
							+ "\n\t3. To search a book by title."
							+ "\n\t4. To search a book by autor's name."
							+ "\n\t5. To calculate the price of all available books."
							+ "\n\t6. To add a new book."
							+ "\n\t7. To remove a book."
							+ "\n\t8. To sell a book."
							+ "\n\t9. To load a new xml file from publisher."
							+ "\n\t10. To quit and save all changes.");
					options = 0;
					options = input2.nextInt();
						switch (options) {
							case 1:	System.out.println(BookMethods.showAllInfo(getBookMap()));; break;
							case 2:	BookMethods.increasePrice(bookMap); break;
							case 3:	System.out.println(BookMethods.searchByTitle(getBookMap()));; break;
							case 4:	System.out.println(BookMethods.searchByAutor(getBookMap()));; break;
							case 5:	double sum = BookMethods.calculateAllPrices(getBookMap());
									System.out.printf("\n%s%.2f\n","Total sum of all books is: ", sum); break;
							case 6:	BookMethods.bookAdding(bookMap); break;
							case 7:	BookMethods.removeBook(bookMap); break;
							case 8:	double totalSum = BookMethods.sellingBook(getBookMap());
									System.out.printf("\n%s%.2f\n\n","Total sum of all books is: ", totalSum); break;
							case 9:	setBookMap(BookMethods.loadingNewXML(bookMap, setFileName())); break;		
							case 10: this.quitAndSaveAll(getBookMap()); exit = false; break;
							default: System.err.println("Please enter a correct number!!!"); break;
						}
				} while (exit);
				
			}
		} while (exit);
	}
	
	private void readSerFile(){
		ReadSerFileBook read = new ReadSerFileBook();		
		try {
			setBookMap(read.readFromXMLFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	private void quitAndSaveAll(HashMap<String, Book> bookMap){
		WriteSeeBookFile write = new WriteSeeBookFile();
		write.addRecords(bookMap);
	}

	private String setFileName() {
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter file name!...");
		String fileName = input.nextLine();

		return fileName;
	}
	
	public HashMap<String, Book> getBookMap() {
		return bookMap;
	}

	public void setBookMap(HashMap<String, Book> bookMap) {
		this.bookMap = bookMap;
	}

}

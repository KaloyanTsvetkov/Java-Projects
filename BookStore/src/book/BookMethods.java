package book;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

public class BookMethods {

	public static void bookAdding(HashMap<String, Book> bookMap) {
		Scanner input = new Scanner(System.in);
		String autor;
		String bookTitle;
		String isbn;
		double bookPrice;
		String publisher;
		int quantity;

		System.out.println("Please enter autor's name: ");
		autor = input.nextLine();
		System.out.println("Please enter book title: ");
		bookTitle = input.nextLine();
		System.out.println("Please enter isbn: ");
		isbn = input.nextLine();
		System.out.println("Please enter book price: ");
		bookPrice = input.nextDouble();
		System.out.println("Please enter publisher's name: ");
		publisher = input.nextLine();
		publisher = input.nextLine();
		System.out.println("Please enter quantity: ");
		quantity = input.nextInt();

		Book book = new Book(autor, bookTitle, isbn, bookPrice, publisher, quantity);
		if (!bookMap.containsKey(bookTitle)) {
			bookMap.put(bookTitle, book);
		}
		
//		for (Map.Entry<String, Book> subject : bookMap.entrySet()) {
//			System.out.println(subject);
//		}
	}

	public static HashMap<String, Book> loadingBookStore(String fileName){
		HashMap<String, Book> bookMap = new HashMap<String, Book>();
		try {
			bookMap = ReadingXML.readingXmlFile(fileName);
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookMap;
	}
	
	public static  HashMap<String, Book> loadingNewXML(HashMap<String, Book> bookMap, String fileName){
		HashMap<String, Book> tempBookMap = new HashMap<String, Book>();
		tempBookMap = loadingBookStore(fileName);
		for (Map.Entry<String, Book> book : tempBookMap.entrySet()) {
			bookMap.put(book.getValue().getBookTitle(), book.getValue());
		}		
		return bookMap;
	}

	public static String showAllInfo(HashMap<String, Book> bookMap){
		StringBuilder sb = new StringBuilder();
		
		for (Map.Entry<String, Book> book : bookMap.entrySet()) {
			sb.append("\n").append(book);
		}
		String result = sb.toString();
		return result;
	}

	public static void increasePrice(HashMap<String, Book> bookMap) {
		double newPrice;
		for(Map.Entry<String, Book> book : bookMap.entrySet()) {
			newPrice = book.getValue().getBookPrice()*1.2;
			book.getValue().setBookPrice(newPrice);
		}
	}

	public static String searchByAutor(HashMap<String, Book> bookMap){
		Scanner input = new Scanner(System.in);
		String searchAutor;
		boolean flag = false;
		String result = "";
		System.out.println("Please enter autor name... ");
		searchAutor = input.nextLine();
		
		for(Map.Entry<String, Book> book : bookMap.entrySet()) {
			if(book.getValue().getAutor().equalsIgnoreCase(searchAutor)){
				result = result + "\n" + book;
				flag = true;
			}
		}
		if(flag==false){
			result = result + "We don't have a books from this autor!!!\n";
		}
		
		return result;
	}

	public static String searchByTitle(HashMap<String, Book> bookMap){
		Scanner input = new Scanner(System.in);
		String searchTitle;
		String result = "";
		System.out.println("Please enter book title... ");
		searchTitle = input.nextLine();
		boolean flag = false;
		for(Map.Entry<String, Book> book : bookMap.entrySet()) {
			if(book.getValue().getBookTitle().equalsIgnoreCase(searchTitle)){
				result = result + "\n" + book;
				flag = true;
			}
		}
		if(flag==false){
			result = result + "We don't have a book with this title!!!\n";
		}
		
		return result;
	}

	public static double calculateAllPrices(HashMap<String, Book> bookMap){
		double sum = 0.00;
		for(Map.Entry<String, Book> book : bookMap.entrySet()) {
			sum = sum + (book.getValue().getQuantity()* book.getValue().getBookPrice());
		}
		return sum;
	}

	public static void removeBook(HashMap<String, Book> bookMap){
		Scanner input = new Scanner(System.in);
		String deleteTitle;
		System.out.println("Please enter book title... ");
		deleteTitle = input.nextLine();
		if(bookMap.containsKey(deleteTitle)){
			bookMap.remove(deleteTitle);
		}
		else{
			System.err.println("You dont't have a book with name " + deleteTitle);
		}
		
	}

	public static double sellingBook(HashMap<String, Book> bookMap){
		Scanner input = new Scanner(System.in);
		String sellTitle;
		int quantity;
		int finalQuantity;
		System.out.println("Please enter book title... ");
		sellTitle = input.nextLine();
		double totalPrice= 0.00;
		for(Map.Entry<String, Book> book : bookMap.entrySet()) {
			if(book.getValue().getBookTitle().equalsIgnoreCase(sellTitle)){
				System.out.println("How many quantity you want? ");
				quantity = input.nextInt();
				finalQuantity = book.getValue().getQuantity();
				if(quantity<=finalQuantity){
					totalPrice = book.getValue().getBookPrice()*quantity;
					finalQuantity = finalQuantity - quantity;
					book.getValue().setQuantity(finalQuantity);
				}
				else{
					System.err.println("We don't have so many books available.\n"
							+ "We have " + book.getValue().getQuantity() + " on hand!!!");
				}
				
			}
		}
		
		return totalPrice;
	}


}

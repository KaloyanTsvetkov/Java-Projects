package book;

import java.io.Serializable;
import java.util.Scanner;

public class Book implements Serializable{

	private static final long serialVersionUID = 1L;
	private String autor;
	private String bookTitle;
	private String isbn;
	private double bookPrice;
	private String publisher;
	private int quantity;
	
	
	public Book(String autor, String bookTitle, String isbn, double bookPrice,
			String publisher, int quantity) {
		setAutor(autor);
		setBookTitle(bookTitle);
		setIsbn(isbn);
		setBookPrice(bookPrice);
		setPublisher(publisher);
		setQuantity(quantity);
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		try {
			if (!autor.isEmpty()) {
				this.autor = autor;
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			System.err.println("Please enter a autor name: ");
			Scanner input = new Scanner(System.in);
			setAutor(input.nextLine());
			input.close();
		}
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		try {
			if (!bookTitle.isEmpty()) {
				this.bookTitle = bookTitle;
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			System.err.println("Please enter a book title: ");
			Scanner input = new Scanner(System.in);
			setBookTitle(input.nextLine());
			input.close();
		}

	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		try {
			if (!isbn.isEmpty()) {
				this.isbn = isbn;
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			System.err.println("Please enter a book isbn: ");
			Scanner input = new Scanner(System.in);
			setIsbn(input.nextLine());
			input.close();
		}

	}

	public double getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(double bookPrice) {
		try {
			if (bookPrice>0.00) {
				this.bookPrice = bookPrice;
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			System.err.println("Please enter a book price: ");
			Scanner input = new Scanner(System.in);
			setBookPrice(input.nextDouble());
			input.close();
		}

	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		try {
			if (!publisher.isEmpty()) {
				this.publisher = publisher;
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			System.err.println("Please enter a publisher namen: ");
			Scanner input = new Scanner(System.in);
			setPublisher(input.nextLine());
			input.close();
		}
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		try {
			if (quantity>=0) {
				this.quantity = quantity;
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			System.err.println("Please enter a publisher namen: ");
			Scanner input = new Scanner(System.in);
			setQuantity(input.nextInt());
			input.close();
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Autor's name: ").append(getAutor());
		sb.append("\nBook title: ").append(getBookTitle());
		sb.append("\nIsbn: ").append(getIsbn());
		sb.append("\nPrice: ").append(getBookPrice());
		sb.append("\nPublisher's name: ").append(getPublisher());
		sb.append("\nQuantity: ").append(getQuantity());
		sb.append("\n");
				
		return sb.toString();
	}

	
}

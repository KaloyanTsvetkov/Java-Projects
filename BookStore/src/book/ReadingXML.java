package book;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadingXML {

	public static HashMap<String, Book> readingXmlFile(String fileName)
			throws ParserConfigurationException, SAXException, IOException,
			XPathExpressionException {
		HashMap<String, Book> bookMap = new HashMap<String, Book>();
		Scanner input = new Scanner(System.in);

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = dbf.newDocumentBuilder();
		Document doc = docBuilder.parse(fileName);

		XPathFactory xpFactory = XPathFactory.newInstance();
		XPath xpath = xpFactory.newXPath();
		NodeList nodes = (NodeList) xpath.evaluate("/Books/Book[price>10.00]",
				doc, XPathConstants.NODESET);

		for (int index = 0; index < nodes.getLength(); index++) {
			Node booksNodes = nodes.item(index);

			String autor = xpath.evaluate("autor", booksNodes);
			System.out.println("book autor: " + autor);
			String title = xpath.evaluate("title", booksNodes);
			System.out.println("Book name: " + title);
			String isbn = xpath.evaluate("isbn", booksNodes);
			System.out.println("books isbn:" + isbn);
			String price = xpath.evaluate("price", booksNodes);
			double priceDouble = Double.parseDouble(price);
			System.out.println("book price: " + priceDouble);
			String publisher = xpath.evaluate("publisher", booksNodes);
			System.out.println("publisher: " + publisher);
			System.out.println("Please enter the quantity you want: ");
			int quantity = input.nextInt();

			Book book = new Book(autor, title, isbn, priceDouble, publisher,
					quantity);
			bookMap.put(title, book);
		}
		
		return bookMap;
	}

}

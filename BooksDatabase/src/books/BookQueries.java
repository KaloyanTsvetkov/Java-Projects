package books;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookQueries {

    private Connection connection = null;
    private PreparedStatement selectAllAuthors = null;
    private PreparedStatement selectBookByAuthor = null;
    private PreparedStatement selectAuthorByTitle = null;
    private PreparedStatement insertNewAuthor = null;
    private PreparedStatement selectAllBooks = null;
    private PreparedStatement insertIntoTitle = null;
    private PreparedStatement insertIntoAuthorISBN = null;
    private HashMap<Integer, Author> authorMap;
    private HashMap<String, Book> bookMap;

    public BookQueries() throws ClassNotFoundException {
        authorMap = new HashMap<Integer, Author>();
        bookMap = new HashMap<String, Book>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/books", "root", "");

            selectAllAuthors
                    = connection.prepareStatement("SELECT * FROM authors");

            selectAllBooks = connection.prepareStatement("select * from titles");

            selectBookByAuthor = connection.prepareStatement(
                    "SELECT B.ISBN, B.title, B.EDITION_NUBER, B.copyright FROM titles B"
                    + " JOIN authorisbn T ON B.ISBN = T.ISBN"
                    + " JOIN authors A ON T.AUTHOR_ID = A.AUTHOR_ID"
                    + " WHERE A.LAST_NAME = ? AND A.FIRST_NAME = ?");

            selectAuthorByTitle = connection.prepareStatement(
                    "SELECT A.LAST_NAME, A.FIRST_NAME, A.AUTHOR_ID FROM authors A"
                    + " JOIN authorisbn T ON A.AUTHOR_ID = T.AUTHOR_ID"
                    + " JOIN titles B ON T.ISBN = B.ISBN"
                    + " WHERE B.TITLE = ?");

            insertNewAuthor = connection.prepareStatement("INSERT INTO authors"
                    + " (FIRST_NAME, Last_Name)"
                    + " VALUES ( ?, ?)");

            insertIntoTitle = connection.prepareStatement("INSERT INTO titles "
                    + "(isbn, title, edition_nuber, copyright) "
                    + "VALUES (?, ?, ?, ?)");

            insertIntoAuthorISBN = connection.prepareStatement("INSERT INTO authorisbn "
                    + "(AUTHOR_ID, ISBN) VALUES (? , ?)");

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.exit(1);
        }
    }

    public List<Author> getAuthorByTitle(String title) {
        ResultSet resultSet = null;
        Author author;
        List<Author> authorList = null;
        try {
            selectAuthorByTitle.setString(1, title);
            resultSet = selectAuthorByTitle.executeQuery();
            authorList = new ArrayList<Author>();

            while (resultSet.next()) {
                author = new Author(
                        resultSet.getString("first_Name"),
                        resultSet.getString("last_Name"),
                        resultSet.getInt("author_ID"));

                authorList.add(author);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return authorList;
    }

    public HashMap<String, Book> getAllBooks() {
        ResultSet resultSet = null;
        Book book;
        try {
            resultSet = selectAllBooks.executeQuery();

            while (resultSet.next()) {
                book = new Book(
                        resultSet.getString("isbn"),
                        resultSet.getString("title"),
                        resultSet.getInt("edition_nuber"),
                        resultSet.getString("copyright"));

                bookMap.put(book.getIsbn(), book);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookQueries.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return bookMap;
    }

    public HashMap<Integer, Author> getAllAuthor() {
        ResultSet resultSet = null;
        Author author;
        try {
            resultSet = selectAllAuthors.executeQuery();

            while (resultSet.next()) {
                author = new Author(
                        resultSet.getString("first_Name"),
                        resultSet.getString("last_Name"),
                        resultSet.getInt("author_ID"));

                authorMap.put(author.getAuthorID(), author);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookQueries.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return authorMap;
    }

    public List<Book> getBookByAuthor(String firstName, String lastName) {
        List<Book> bookList = null;
        ResultSet resultSet = null;
        try {
            selectBookByAuthor.setString(1, lastName);
            selectBookByAuthor.setString(2, firstName);
            resultSet = selectBookByAuthor.executeQuery();
            bookList = new ArrayList<Book>();
            while (resultSet.next()) {
                bookList.add(new Book(
                        resultSet.getString("isbn"),
                        resultSet.getString("title"),
                        resultSet.getInt("edition_nuber"),
                        resultSet.getString("copyright")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookQueries.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return bookList;
    }

    public int addAuthor(String firstName, String lastName) {
        int result = 0;

        try {
            insertNewAuthor.setString(1, firstName);
            insertNewAuthor.setString(2, lastName);

            result = insertNewAuthor.executeUpdate();
            authorMap = getAllAuthor();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return result;
    }

    public boolean insertBookByAuthor(int authorID, String isbn, String title, int edition, String copyright) {
        int result = 0;
        boolean flag = true;
        try {
            connection.setAutoCommit(false);

            insertIntoTitle.setString(1, isbn);
            insertIntoTitle.setString(2, title);
            insertIntoTitle.setInt(3, edition);
            insertIntoTitle.setString(4, copyright);

            result = insertIntoTitle.executeUpdate();
            if (result != 1) {
                connection.rollback();
                flag = false;
            }

            insertIntoAuthorISBN.setInt(1, authorID);
            insertIntoAuthorISBN.setString(2, isbn);

            result = insertIntoAuthorISBN.executeUpdate();
            if (result != 1) {
                connection.rollback();
                flag = false;
            }

            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return flag;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}


package books;

public class Book {
    
    private String isbn;
    private String title;
    private int edition;
    private String copyright;
    
    public Book(String isbn, String title, int edition, String copyright){
        setIsbn(isbn);
        setTitle(title);
        setEdition(edition);
        setCopyright(copyright);
    }


    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    @Override
    public String toString() {
        String result = "";
        result = result + getTitle(); 
        return result;
    }
    
    
    
}

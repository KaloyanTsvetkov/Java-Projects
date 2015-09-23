
package books;

public class Author {
    
   private String firstName;
   private String lastName;
   private int authorID;
     
   public Author(String firstName, String lastName, int authorID){
       setFirstName(firstName);
       setLastName(lastName);
       setAuthorID(authorID);
   }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName; 
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    @Override
    public String toString() {
        String result = "";
        result = result +getAuthorID() + ". " +  getFirstName() + " " + getLastName();
   
        return result;
    }
   
    
    
}

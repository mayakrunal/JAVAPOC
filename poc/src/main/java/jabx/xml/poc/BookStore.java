package jabx.xml.poc;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//This statement means that class"Bookstore.java"is the root-element of our example
@XmlRootElement(namespace = "de.vogella.xml.jaxb.model")
public class BookStore {

    // XmLElementWrapper generates a wrapper element around XML representation
    // @XmlElementWrapper(name = "bookList")
    // XmlElement sets the name of the entities
    @XmlElement(name = "book")
    private ArrayList<Book> bookList;

    private String name;

    private String location;

    public void setBookList(final ArrayList<Book> bookList) {
        this.bookList = bookList;
    }

    public ArrayList<Book> getBooksList() {
        return bookList;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(final String location) {
        this.location = location;
    }
}
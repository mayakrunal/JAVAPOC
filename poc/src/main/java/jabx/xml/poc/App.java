package jabx.xml.poc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/** Hello world! */
public class App {

    private static final String BOOKSTORE_XML = "./bookstore-jaxb.xml";

    public static void main(final String[] args) throws JAXBException, FileNotFoundException {
        final ArrayList<Book> bookList = new ArrayList<>();

        // create books
        final Book book1 = new Book();
        book1.setIsbn("978-0060554736");
        book1.setName("The Game");
        book1.setAuthor("Neil Strauss");
        book1.setPublisher("Harpercollins");
        bookList.add(book1);

        final Book book2 = new Book();
        book2.setIsbn("978-3832180577");
        book2.setName("Feuchtgebiete");
        book2.setAuthor("Charlotte Roche");
        book2.setPublisher("Dumont Buchverlag");
        bookList.add(book2);

        // create bookstore, assigning book
        final BookStore bookstore = new BookStore();
        bookstore.setName("Fraport Bookstore");
        bookstore.setLocation("Frankfurt Airport");
        bookstore.setBookList(bookList);

        // create JAXB context and instantiate marshaller
        final JAXBContext context = JAXBContext.newInstance(BookStore.class);
        final Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        // Write to System.out
        m.marshal(bookstore, System.out);

        // Write to File
        m.marshal(bookstore, new File(BOOKSTORE_XML));

        // get variables from our xml file, created before
        System.out.println();
        System.out.println("Output from our XML File: ");
        final Unmarshaller um = context.createUnmarshaller();
        final BookStore bookstore2 = (BookStore) um.unmarshal(new FileReader(BOOKSTORE_XML));
        final ArrayList<Book> list = bookstore2.getBooksList();
        for (final Book book : list) {
            System.out.println("Book: " + book.getName() + " from " + book.getAuthor());
        }
    }
}

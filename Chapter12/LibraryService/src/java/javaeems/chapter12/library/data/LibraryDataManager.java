package javaeems.chapter12.library.data;

import java.util.List;
import javax.ejb.Remote;

@Remote
public interface LibraryDataManager {

    public static String ALL = "All";
    public static String NOVEL = "Novel";
    public static String REFERENCE = "Reference";
    public static String HUMOR = "Humor";

    public void removeBook(int id);

    public List<Book> getBooks(String searchGenre);

    public void addBook(String title, String author, String genre);
}

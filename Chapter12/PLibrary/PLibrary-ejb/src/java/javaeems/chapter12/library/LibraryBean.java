package javaeems.chapter12.library;

import java.util.*;
import javaeems.chapter12.library.entities.*;
import javax.ejb.Remote;

@Remote
public interface LibraryBean {
    public void addAuthor(List<String> firstNames, String lastName, String description);
    public List<Author> getAuthors();
    public Author getAuthorForId(int id);
    public List<Book> getBooksByAuthor(int authorID);
    public void addBook(String title, int authorId, List<String> genres, boolean isChildrens, int age);
    public void deleteBook(int bookId);
    public List<Book> getBooksByGenre(String genreName);
    public Genre getGenreByName(String name);
    public List<String> getGenreNames();
    
}

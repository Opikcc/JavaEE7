package javaeems.chapter15.library;

import javaeems.chapter15.library.entities.Book;
import javaeems.chapter15.library.entities.Author;
import javaeems.chapter15.library.entities.Genre;
import java.util.*;
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

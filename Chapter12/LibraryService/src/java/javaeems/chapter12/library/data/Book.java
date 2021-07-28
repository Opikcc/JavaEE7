package javaeems.chapter12.library.data;

import javax.persistence.*;
import java.io.*;

@Entity
@NamedQueries({
    @NamedQuery(
            name = "findAllBooks",
            query = "select b from Book b"
    ),
    @NamedQuery(
            name = "findBooksByGenre",
            query = "select b from Book b where b.genre = :genre"
    )
}
)
public class Book implements Serializable {

    @Id
    private int id;
    private String title;
    private String author;
    private String genre;

    public Book() {
    }

    public Book(int id, String title, String author, String genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "a book by " + this.author;
    }
}

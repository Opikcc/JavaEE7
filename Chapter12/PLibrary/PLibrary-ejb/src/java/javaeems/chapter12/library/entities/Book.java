package javaeems.chapter12.library.entities;

import javax.persistence.*;
import java.io.*;
import java.util.List;

@Entity
@NamedQueries({ 
    @NamedQuery(
        name="findAllBooks",
        query="select b from Book b order by b.title"
    ),
    
    @NamedQuery(
        name="findAllBooksBy",
        query="select b from Book b where b.author.id = :aId order by b.title"
    )
    }
)
public class Book implements Serializable {
    @Id
    private int id;
    private String title;
    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.REFRESH})
    private Author author;
    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Genre> genres;
    
    public Book() {}
    
    public Book(int id, String title, Author author, List<Genre> genres) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genres = genres;
    }
    
    public int getId() {
        return this.id;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public Author getAuthor() {
        return this.author;
    }
    
    public List<Genre> getGenres() {
        return this.genres;
    }
    
    public boolean isInGenre(String name) {
        for (Genre g : this.genres) {
            if (g.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "a book called " + this.title + " by " + this.author + " in " + this.genres;
    }
}

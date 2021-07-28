package javaeems.chapter12.library.web;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.ejb.*;
import java.io.Serializable;
import javaeems.chapter12.library.LibraryBean;
import javaeems.chapter12.library.entities.Book;
import javaeems.chapter12.library.entities.*;
import javaeems.chapter12.library.entities.Author;
import java.util.List;


@Named("libraryBean")
@SessionScoped
public class LibraryJSFBean implements Serializable {
    @EJB
    LibraryBean librarybean;
    private String currentGenreName;
    private Author currentAuthor;
    private Book currentBook;
    
    public LibraryJSFBean() {
  
    }
    
    public String getIconForBook(Book b) {
        if (!(b instanceof ChildrensBook)) {
            return "Adult.png";
        }
        ChildrensBook cb = (ChildrensBook) b;
        if (cb.getAge() < 5) {
            return "Baby-icon.png";
        } else {
            return "kid.png";
        }
    }
    
    public String getTitle() {
        return "hello !";
    }
    
    public String getGenreDescriptionFor(String name) {
        Genre genre = librarybean.getGenreByName(name);
        return genre.getDescription();
    }
    
    public List<Book> getBooksBy(Author a) {
        return librarybean.getBooksByAuthor(a.getId());
    }
    
    public List<Author> getAuthors() {
        return librarybean.getAuthors();
    }
    
    public void setCurrentAuthor(Author currentAuthor) {
        this.currentAuthor = currentAuthor;
    }
    
    public List<String> getGenreNames() {
        return librarybean.getGenreNames();
    }
    
    public void setCurrentGenreName(String currentGenreName) {
        this.currentGenreName = currentGenreName;
    }
    
    public String getCurrentGenreName() {
        if (this.currentGenreName == null) {
            this.currentGenreName = librarybean.getGenreNames().get(0);
        }
        return this.currentGenreName;
    }
    
    public List<Book> getBooks() {
        return librarybean.getBooksByGenre(currentGenreName);
    }
    
    public void setCurrentAuthorId(int id) {
        List<Author> authors = librarybean.getAuthors();
        for (Author a : authors) {
            if (a.getId() == id) {
                this.currentAuthor = a;
            }
        }
        
    }
    
    public int getCurrentAuthorId() {
        return this.currentAuthor.getId();
    }
    
    public Author getCurrentAuthor() {
        return this.currentAuthor;
    } 
    
    public Book getCurrentBook() {
        return this.currentBook;
    }
    
    public void deleteBook(Book book) {
        this.librarybean.deleteBook(book.getId());
    }
    
}




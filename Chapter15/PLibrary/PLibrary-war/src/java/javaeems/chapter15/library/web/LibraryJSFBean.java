package javaeems.chapter15.library.web;

import javaeems.chapter15.library.entities.Genre;
import javaeems.chapter15.library.entities.ChildrensBook;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.ejb.*;
import java.io.Serializable;
import javaeems.chapter15.library.LibraryBean;
import javaeems.chapter15.library.entities.Book;
import javaeems.chapter15.library.entities.Author;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@Named("libraryBean")
@SessionScoped
public class LibraryJSFBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    LibraryBean librarybean;
    private String currentGenreName;
    private Author currentAuthor;
    private Book currentBook;

    public LibraryJSFBean() {
    }

    public String getUsername() {
        FacesContext facesContext = FacesContext.getCurrentInstance();

        if (facesContext != null) {
            return facesContext.getExternalContext().getUserPrincipal().getName();
        }
        return "Unknown";
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
        try {
            this.librarybean.deleteBook(book.getId());
        } catch (EJBAccessException e) {
            throw new UnauthorizedAccessException("Error deleting book", librarybean.getClass().getSimpleName(), "deleteBook");
        }
    }

    public void logout() {
        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).invalidate();
    }

}

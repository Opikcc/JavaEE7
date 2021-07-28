package javaeems.chapter15.library.impl;

import java.util.ArrayList;
import java.util.List;
import javaeems.chapter15.library.LibraryBean;
import javaeems.chapter15.library.entities.Author;
import javaeems.chapter15.library.entities.Book;
import javaeems.chapter15.library.entities.ChildrensBook;
import javaeems.chapter15.library.entities.Genre;
import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@PermitAll
@Singleton
@DeclareRoles({"adult", "child", "adminstrator"})
public class LibraryBeanImpl implements LibraryBean {

    @PersistenceContext
    private EntityManager em;
    @Resource
    SessionContext sessionContext;

    @Override
    public List<Book> getBooksByGenre(String genreName) {
        Query query = em.createNamedQuery("findAllBooks");
        List<Book> books = query.getResultList();
        List<Book> genreBooks = new ArrayList<>();
        for (Book b : books) {
            if (b.isInGenre(genreName)) {
                if (sessionContext.isCallerInRole("child")) {
                    if (b instanceof ChildrensBook) {
                        genreBooks.add(b);
                    }
                } else if (sessionContext.isCallerInRole("adult")) {
                    if (!(b instanceof ChildrensBook)) {
                        genreBooks.add(b);
                    }
                } else {
                    genreBooks.add(b);
                }
            }
        }
        return genreBooks;
    }

    @RolesAllowed("administrator")
    @Override
    public void addBook(String title, int authorId, List<String> genreNames, boolean isChildrens, int age) {
        int id = this.generateNewBookId();
        Author author = this.getAuthorForId(authorId);
        if (author == null) {
            throw new RuntimeException("bad author");
        }
        List<Genre> genres = this.getGenres(genreNames);
        Book newBook;
        if (isChildrens) {
            newBook = new ChildrensBook(id, title, author, genres, age);
        } else {
            newBook = new Book(id, title, author, genres);
        }
        em.persist(newBook);

    }

    @RolesAllowed("administrator")
    @Override
    public void addAuthor(List<String> foreNames, String lastName, String description) {
        int id = this.generateNewAuthorId();
        Author a = new Author(id, foreNames, lastName, description);
        em.persist(a);
    }

    @RolesAllowed("administrator")
    @Override
    public void deleteBook(int id) {
        Book b = em.find(Book.class, id);
        em.remove(b);
    }

    @Override
    public List<Book> getBooksByAuthor(int authorID) {
        List<Book> books = new ArrayList<>();
        Query query = em.createNamedQuery("findAllBooksBy");
        query.setParameter("aId", authorID);
        return query.getResultList();

    }

    @Override
    public Genre getGenreByName(String name) {
        Query query = em.createNamedQuery("findByGenreName");
        query.setParameter("gName", name);
        return (Genre) query.getSingleResult();
    }

    @Override
    public List<Author> getAuthors() {
        Query query = em.createNamedQuery("findAllAuthors");
        List<Author> authors = query.getResultList();
        return authors;
    }

    private List<Genre> getGenres(List<String> genreNames) {
        List<Genre> genres = new ArrayList<>();
        for (String name : genreNames) {
            genres.add(this.getGenreByName(name));
        }
        return genres;
    }

    @Override
    public List<String> getGenreNames() {
        Query query = em.createNamedQuery("findAllGenres");
        List<Genre> genres = query.getResultList();
        List<String> names = new ArrayList<>();
        for (Genre g : genres) {
            names.add(g.getName());
        }
        return names;
    }

    @Override
    public Author getAuthorForId(int id) {
        Query query = em.createNamedQuery("findAuthorById");
        query.setParameter("aId", id);
        return (Author) query.getSingleResult();

    }

    private int generateNewAuthorId() {
        for (int i = 0; i < 30000; i++) {
            Author author = em.find(Author.class, i);
            if (author == null) {
                return i;
            }
        }
        throw new RuntimeException("xxx");
    }

    private int generateNewBookId() {
        for (int i = 0; i < 30000; i++) {
            Book b = em.find(Book.class, i);
            if (b == null) {
                return i;
            }
        }
        throw new RuntimeException("xxx");
    }

}

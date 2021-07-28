package javaeems.chapter12.library.data;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.annotation.PostConstruct;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Singleton
@Startup
public class JPALibraryDataManagerImpl implements LibraryDataManager {

    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    public void initializeDefaultData() {
        for (Book b : this.getDefaultBooks()) {
            this.addBook(b);
        }
    }

    @Override
    public void removeBook(int id) {
        Book b = this.em.find(Book.class, id);
        this.em.remove(b);
    }

    @Override
    public List<Book> getBooks(String searchGenre) {
        List<Book> books;
        if (LibraryDataManager.ALL.equals(searchGenre)) {
            books = em.createNamedQuery("findAllBooks").getResultList();
        } else {
            Query query = em.createNamedQuery("findBooksByGenre");
            query.setParameter("genre", searchGenre);
            books = query.getResultList();
        }
        return books;
    }

    private void addBook(Book book) {
        this.em.persist(book);
    }

    @Override
    public void addBook(String title, String author, String genre) {
        Book book = new Book(this.generateId(), title, author, genre);
        this.addBook(book);
    }

    public int generateId() {
        long l = System.currentTimeMillis() * (new Random()).nextInt();
        String asString = "" + l;
        String as5String = asString.substring((asString.length() - 5), (asString.length()));
        return new Integer(as5String);
    }

    private List<Book> getDefaultBooks() {
        List<Book> books = new ArrayList<>();
        Book b = new Book(this.generateId(), "A Passage to India", "E M Forster", LibraryDataManager.NOVEL);
        books.add(b);
        b = new Book(this.generateId(), "Damp Bedsheets", "I P Nightly", LibraryDataManager.HUMOR);
        books.add(b);
        b = new Book(this.generateId(), "Sense and Sensibility", "Jane Austen", LibraryDataManager.NOVEL);
        books.add(b);
        b = new Book(this.generateId(), "The Stranger", "Albert Camus", LibraryDataManager.NOVEL);
        books.add(b);
        b = new Book(this.generateId(), "How the Dinosaurs Died", "P T Dactyl", LibraryDataManager.REFERENCE);
        books.add(b);
        b = new Book(this.generateId(), "Too close to the edge ?", "Eileen Dover", LibraryDataManager.HUMOR);
        books.add(b);
        b = new Book(this.generateId(), "Houseplants for Dummies", "G Fingers", LibraryDataManager.REFERENCE);
        books.add(b);
        b = new Book(this.generateId(), "Disguises in History", "Ivor Beard", LibraryDataManager.HUMOR);
        books.add(b);
        b = new Book(this.generateId(), "10,000 Knitting Patterns", "M N E Sweaters", LibraryDataManager.REFERENCE);
        books.add(b);
        return books;
    }

}

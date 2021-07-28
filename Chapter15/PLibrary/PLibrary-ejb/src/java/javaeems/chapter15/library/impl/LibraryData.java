package javaeems.chapter15.library.impl;

import java.util.ArrayList;
import java.util.List;
import javaeems.chapter15.library.entities.Author;
import javaeems.chapter15.library.entities.Book;
import javaeems.chapter15.library.entities.ChildrensBook;
import javaeems.chapter15.library.entities.Genre;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Startup
public class LibraryData {

    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    public void init() {
        for (Book b : getBooks()) {
            em.persist(b);
        }
    }

    public static List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        Genre novel = new Genre("Novel", "Fictitious prose narratives of book length, typically representing character and \naction with some degree of realism.");
        Genre detective = new Genre("Detective", "Book involving a mystery and a person who solves it.");
        Genre scifi = new Genre("SciFi", "Fictional stories often set in the future and/or in space");
        Genre biography = new Genre("Biography", "This genre contains biographies of people.");
        Genre reference = new Genre("Reference", "Books that are full of facts.");
        List<String> fNames = new ArrayList<>();
        fNames.add("Sir");
        fNames.add("Arthur");
        fNames.add("Conrad");
        Author a = new Author(2, fNames, "Boyle", "A Welsh physician and writer who is most noted for his fictional stories about the detective Herlock Sholmes.");

        List<Genre> genres = new ArrayList<>();
        genres.add(novel);
        genres.add(detective);

        Book b = new Book(1, "The Adventures of Herlock Sholmes", a, genres);
        books.add(b);
        b = new Book(2, "The Return of Herlock Sholmes", a, genres);
        books.add(b);

        ChildrensBook cb = new ChildrensBook(3, "Herlock Sholmes, a picture mystery !", a, genres, 4);
        books.add(cb);

        genres = new ArrayList<>();
        genres.add(novel);

        a = new Author(3, "Sallie", "James", "A wonderful childrens writer from Leeds.");
        cb = new ChildrensBook(4, "Speak like an animal !", a, genres, 4);

        books.add(cb);

        genres = new ArrayList<>();
        genres.add(reference);
        cb = new ChildrensBook(5, "What animals eat !", a, genres, 9);
        books.add(cb);

        genres = new ArrayList<>();
        genres.add(novel);
        genres.add(scifi);
        b = new Book(6, "Lost on Neptune", a, genres);
        books.add(b);

        b = new Book(7, "I am Zog", a, genres);
        books.add(b);

        genres = new ArrayList<>();
        genres.add(biography);
        genres.add(detective);
        b = new Book(8, "The life of Sir Aurthur Conrad Boyle", a, genres);
        books.add(b);

        genres = new ArrayList<>();
        genres.add(biography);
        genres.add(scifi);
        genres.add(reference);
        b = new ChildrensBook(9, "Zog: My story from egg to chrysalis.", a, genres, 10);
        books.add(b);

        genres = new ArrayList<>();
        genres.add(scifi);

        b = new ChildrensBook(10, "Zooming through space !!!", a, genres, 3);
        books.add(b);

        a = new Author(4, "Miriam Anne", "Reid", "A biographer and novellist of the highest order.");
        genres = new ArrayList<>();
        genres.add(novel);

        b = new Book(11, "A Green House", a, genres);
        books.add(b);
        b = new Book(12, "Ligthouse at dusk", a, genres);
        books.add(b);
        genres = new ArrayList<>();
        genres.add(novel);
        genres.add(scifi);
        b = new ChildrensBook(13, "Tween robot dawn", a, genres, 10);
        books.add(b);
        return books;
    }

}

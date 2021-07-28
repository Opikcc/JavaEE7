package javaeems.chapter5.library;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonArrayBuilder;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.annotation.PostConstruct;

@Path("library")
@ApplicationScoped
public class Library {
    private String NOVEL = "Novel";
    private String REFERENCE = "Reference";
    private String HUMOR = "Humor";
    private List<Book> books = new ArrayList<>();

    @GET
    @Path("/books")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getBooks(@Context UriInfo uriInfo) {
        String genre = uriInfo.getQueryParameters().getFirst("genre");
        JsonArrayBuilder ab = Json.createArrayBuilder();
        for (Book next : this.books) {
            if (next.getGenre().equals(genre) || genre.equals("All")) {
                ab.add(next.getSummaryDescription());
            }
        }
        JsonObject model = Json.createObjectBuilder()
                .add("booklist", ab.build())
                .build();
        return model;
    }
    
    
    @Path("books/{id}")
    public Book getBook(@Context UriInfo uriInfo) {
        String idString =  uriInfo.getPathParameters().get("id").get(0);
        return this.getBook(new Integer(idString));
    }
    
    void removeBook(Book book) {
        this.books.remove(book);
    }
    
    private Book getBook(int id) {
        for (Book book : this.books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }
    
    @GET
    @Path("/genres")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getGenres() {
        JsonArrayBuilder ab = Json.createArrayBuilder()
                .add(NOVEL)
                .add(REFERENCE)
                .add(HUMOR);    
        JsonObject genreso = Json.createObjectBuilder()
            .add("genres", ab)
            .build();
        return genreso;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addBook(JsonObject booko) throws InvalidBookException {
        String genre = booko.getString("genre");
        String title = booko.getString("title");
        String author = booko.getString("author");
        if ("".equals(author)) {
            throw new InvalidBookException("No author name was given !");
        } 
        int id = (int) this.generateId();
        Book b = new Book(
                this,
                id,
                title,
                author,
                genre
                );
        this.books.add(b);
                
    }
    
    private int generateId() {
        long l = System.currentTimeMillis() * (new Random()).nextInt();
        String asString = "" + l;
        String as5String = asString.substring((asString.length()-5), (asString.length()));
        return (new Integer(as5String)).intValue();
        
    }
    
    @PostConstruct
    private void initLibrary() {
        Book b = new Book(this, this.generateId(), "A Passage to India", "E M Forster", NOVEL);
        this.books.add(b);
        b = new Book(this, this.generateId(), "Damp Bedsheets", "I P Nightly", HUMOR);
        this.books.add(b);
        b = new Book(this, this.generateId(), "Sense and Sensibility", "Jane Austen", NOVEL);
        this.books.add(b);
        b = new Book(this, this.generateId(), "The Stranger", "Albert Camus", NOVEL);
        this.books.add(b);
        b = new Book(this, this.generateId(), "How the Dinosaurs Died", "P T Dactyl", REFERENCE);
        this.books.add(b);
        b = new Book(this, this.generateId(), "Too close to the edge ?", "Eileen Dover", HUMOR);
        this.books.add(b);
        b = new Book(this, this.generateId(), "Houseplants for Dummies", "G Fingers", REFERENCE);
        this.books.add(b);
        b = new Book(this, this.generateId(), "Disguises in History", "Ivor Beard", HUMOR);
        this.books.add(b);
        b = new Book(this, this.generateId(), "10,000 Knitting Patterns", "M N E Sweaters", REFERENCE);
        this.books.add(b);
    }
    
    
    
    
}

package javaeems.chapter5.library;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class Book {
    private Library library;
    private int id;
    private String title;
    private String author;
    private String genre;
    
    public Book(Library library, int id, String title, String author, String genre) {
        this.library = library; 
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }
    
    @DELETE
    public void removeFromLibrary() {
        this.library.removeBook(this);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getFullDescription() {        
        JsonObject descriptiono = Json.createObjectBuilder()
            .add("id", this.getId())
            .add("title", this.getTitle())
            .add("author", this.getAuthor())
            .add("genre", this.getGenre())
            .build();
        return descriptiono;
    }
    
    public JsonObject getSummaryDescription() {
        JsonObject descriptiono = Json.createObjectBuilder()
            .add("id", this.getId())
            .add("title", this.getTitle())
            .build();
        return descriptiono;
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
    
    @Override
    public String toString() {
        return "a book by " + this.author;
    }
    
}

package javaeems.chapter12.library;

import javaeems.chapter12.library.data.Book;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class BookEndpoint {
    private LibraryEndpoint library;
    private Book book;
    
    
    public BookEndpoint(LibraryEndpoint library, Book b) {
        this.library = library; 
        this.book = b;
    }
    
    @DELETE
    public void removeFromLibrary() {
        this.library.removeBook(this);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getFullDescription() {        
        JsonObject descriptiono = Json.createObjectBuilder()
            .add("id", this.getBookId())
            .add("title", this.book.getTitle())
            .add("author", this.book.getAuthor())
            .add("genre", this.book.getGenre())
            .build();
        return descriptiono;
    }
    
    public JsonObject getSummaryDescription() {
        JsonObject descriptiono = Json.createObjectBuilder()
            .add("id", this.getBookId())
            .add("title", this.book.getTitle())
            .build();
        return descriptiono;
    }
    
    public int getBookId() {
        return this.book.getId();
    }
    
    public String getBookGenre() {
        return this.book.getGenre();
    }


    
    @Override
    public String toString() {
        return "a book by " + this.book.getAuthor();
    }
    
}

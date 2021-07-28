package javaeems.chapter12.library;

import javaeems.chapter12.library.data.LibraryDataManager;
import javaeems.chapter12.library.data.Book;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import java.util.List;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Path("library")
public class LibraryEndpoint {
    private LibraryDataManager lb;
   
    
    public LibraryEndpoint() {
        try {
            this.lb = InitialContext.doLookup("java:global/LibraryService/JPALibraryDataManagerImpl!javaeems.chapter12.library.data.LibraryDataManager");
            
        } catch (NamingException e) {
            System.out.println(e);
        }
    }

    @GET
    @Path("/books")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getBooks(@Context UriInfo uriInfo) {
        String genre = uriInfo.getQueryParameters().getFirst("genre");
        JsonArrayBuilder ab = Json.createArrayBuilder();
        for (BookEndpoint next : this.getBookEndpoints(genre)) {
            ab.add(next.getSummaryDescription());
            
        }
        JsonObject model = Json.createObjectBuilder()
                .add("booklist", ab.build())
                .build();
        return model;
    }
    
    
    @Path("books/{id}")
    public BookEndpoint getBook(@Context UriInfo uriInfo) {
        String idString =  uriInfo.getPathParameters().get("id").get(0);
        for (BookEndpoint book : this.getBookEndpoints(LibraryDataManager.ALL)) {
            if (book.getBookId() == Integer.parseInt(idString)) {
                return book;
            }
        }
        return null;
    }
    
    void removeBook(BookEndpoint book) {
        this.lb.removeBook(book.getBookId());
    }
 
    
    @GET
    @Path("/genres")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getGenres() {
        JsonArrayBuilder ab = Json.createArrayBuilder()
                .add(LibraryDataManager.NOVEL)
                .add(LibraryDataManager.REFERENCE)
                .add(LibraryDataManager.HUMOR);    
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
        this.lb.addBook(title, author, genre);         
    }
 
    private List<BookEndpoint> getBookEndpoints(String genre) {
        List<BookEndpoint> bookEndpoints = new ArrayList<>();
        for (Book b : this.lb.getBooks(genre)) {
            BookEndpoint be = new BookEndpoint(this, b);
            bookEndpoints.add(be);
        }
        return bookEndpoints;
    }
    
    


    
}

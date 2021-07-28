package javaeems.chapter12.libraryclient;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Entity;
import javax.json.JsonObject;
import javax.json.JsonArray;
import java.util.List;
import java.util.ArrayList;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


public class LibraryClient {
    Client wsClient;
    private static String WEBSERVICE_ROOT = "http://localhost:8080/LibraryService";
    private static String LIBRARY_URI = WEBSERVICE_ROOT + "/library";
    private static String BOOKS_URI = LIBRARY_URI + "/books";
    private static String GENRES_URI = LIBRARY_URI + "/genres";
    
    
    
    public LibraryClient() {
        this.wsClient = ClientBuilder.newBuilder()
                .register(JsonMessageBodyReader.class)
                .register(JsonMessageBodyWriter.class)
                .build();
    }
    
    public String getUriAsString() {
        return LIBRARY_URI;
    }
    
    public List<JsonObject> getBooks(String genre) {
        WebTarget wt = this.wsClient.target(BOOKS_URI + "?genre=" + genre);
        Invocation webServiceCall = wt.request()
                .accept(MediaType.APPLICATION_JSON)
                .build("GET");
        JsonObject genreso = webServiceCall.invoke(JsonObject.class);
        List<JsonObject> bookSummaries = new ArrayList<>();
        
        JsonArray ar = genreso.getJsonArray("booklist");
        for (int i = 0; i < ar.size(); i++) {
            JsonObject jsono = ar.getJsonObject(i);
            bookSummaries.add(jsono); 
        }
        return bookSummaries;
    }
    
    public JsonObject getBookById(int id) {
        WebTarget genresTarget = this.wsClient.target(BOOKS_URI + "/"+ id);
        Invocation webServiceCall = genresTarget.request()
                .accept(MediaType.APPLICATION_JSON)
                .build("GET");
        JsonObject jsono = webServiceCall.invoke(JsonObject.class);
        return jsono;
    }

    
    public void deleteBook(int id) {
        WebTarget genresTarget = this.wsClient.target(BOOKS_URI + "/"+ id);
        Invocation webServiceCall = genresTarget.request()
                .build("DELETE"); 
        webServiceCall.invoke();
    }
    
    public List<String> getGenres() {
        WebTarget genresTarget = wsClient.target(GENRES_URI);
        Invocation webServiceCall = genresTarget.request()
                .accept(MediaType.APPLICATION_JSON)
                .build("GET");
        JsonObject genreso = webServiceCall.invoke(JsonObject.class);
        JsonArray ar = genreso.getJsonArray("genres");
        List<String> genres = new ArrayList<>();
        for (int i = 0; i < ar.size(); i++) {
            String next = ar.getString(i);
            genres.add(next);
        }
        return genres;  
    }
    
    public AddBookStatus addBook(JsonObject bookaddo) {
        WebTarget genresTarget = this.wsClient.target(LIBRARY_URI);
        Invocation webServiceCall = genresTarget.request()
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .build("POST", Entity.json(bookaddo));
        Response response = webServiceCall.invoke();
        if (response.getStatus() == Response.Status.BAD_REQUEST.getStatusCode()) {
            return new AddBookStatus(false, response.readEntity(String.class));
        } else {
            return new AddBookStatus(true, "");
        }
        
    }

}

class AddBookStatus {
    boolean added = false;
    String errorMessage = "";
    
    AddBookStatus(boolean added, String errorMessage) {
        this.added = added;
        this.errorMessage = errorMessage;
    }
}

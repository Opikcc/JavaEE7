package javaeems.chapter15.library.entities;

import java.util.*;
import java.io.*;
import javax.persistence.*;

@Entity
@NamedQueries({
    @NamedQuery(
            name = "findAllAuthors",
            query = "select a from Author a"
    ),
    @NamedQuery(
            name = "findAuthorById",
            query = "select a from Author a where a.id = :aId"
    )
}
)
public class Author implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private int id;
    private List<String> foreNames;
    private String lastName;
    private String description = "";

    public Author() {

    }

    public Author(int id, List<String> foreNames, String lastName, String description) {
        this.id = id;
        this.foreNames = foreNames;
        this.lastName = lastName;
        this.description = description;
    }

    public Author(int id, String firstName, String lastName, String description) {
        this(id, new ArrayList<String>(), lastName, description);
        List<String> foreNames = new ArrayList<>();
        foreNames.add(firstName);
        this.foreNames = foreNames;
    }

    public int getId() {
        return this.id;
    }

    public List<String> getForeNames() {
        return this.foreNames;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getDescription() {
        return this.description;
    }

    public String toString() {
        return "Author:(" + this.foreNames + " " + this.lastName + ")";
    }

}

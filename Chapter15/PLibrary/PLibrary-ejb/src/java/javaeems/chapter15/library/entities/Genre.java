package javaeems.chapter15.library.entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQueries({
    @NamedQuery(
            name = "findAllGenres",
            query = "select g from Genre g"
    ),
    @NamedQuery(
            name = "findByGenreName",
            query = "select g from Genre g where g.name = :gName"
    )
}
)
public class Genre implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String name;
    private String description;

    public String toString() {
        return "genre: " + name + " " + description;
    }

    public Genre(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public Genre() {
    }

}

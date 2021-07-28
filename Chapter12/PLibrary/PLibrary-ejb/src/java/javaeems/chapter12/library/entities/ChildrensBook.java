package javaeems.chapter12.library.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
public class ChildrensBook extends Book implements Serializable {
    private int bestAge;
            
    public ChildrensBook() {}
    
    public ChildrensBook(int id, String title, Author author, List<Genre> genres, int bestAge) {
        super(id, title, author, genres);
        this.bestAge = bestAge;
    }
    
    public int getAge() {
        return bestAge;
    }
    
        @Override
    public String toString() {
        return "a kids book called " + super.getTitle() + " by " + super.getAuthor() + " in " + super.getGenres() + "age " + bestAge;
    }
    
}

package javaeems.chapter15.library.entities;

import javaeems.chapter15.library.entities.Author;
import javaeems.chapter15.library.entities.Genre;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-06-27T16:01:28")
@StaticMetamodel(Book.class)
public class Book_ { 

    public static volatile SingularAttribute<Book, Author> author;
    public static volatile ListAttribute<Book, Genre> genres;
    public static volatile SingularAttribute<Book, Integer> id;
    public static volatile SingularAttribute<Book, String> title;

}
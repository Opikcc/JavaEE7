package javaeems.chapter15.library.entities;

import java.util.List;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-06-27T16:01:28")
@StaticMetamodel(Author.class)
public class Author_ { 

    public static volatile SingularAttribute<Author, String> lastName;
    public static volatile SingularAttribute<Author, List> foreNames;
    public static volatile SingularAttribute<Author, String> description;
    public static volatile SingularAttribute<Author, Integer> id;

}
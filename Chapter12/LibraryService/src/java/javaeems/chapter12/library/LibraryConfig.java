package javaeems.chapter12.library;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


@ApplicationPath("/")
public class LibraryConfig extends Application {
    
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        // register root resource
        classes.add(LibraryEndpoint.class);
        classes.add(JsonMessageBodyWriter.class);
        classes.add(JsonMessageBodyReader.class);
        classes.add(LibraryExceptions.class);
        return classes;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically re-generated by NetBeans REST support to populate
     * given list with all resources defined in the project.
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(javaeems.chapter12.library.BookEndpoint.class);
        resources.add(javaeems.chapter12.library.JsonMessageBodyReader.class);
        resources.add(javaeems.chapter12.library.JsonMessageBodyWriter.class);
        resources.add(javaeems.chapter12.library.LibraryEndpoint.class);
        resources.add(javaeems.chapter12.library.LibraryExceptions.class);
    }

    
}
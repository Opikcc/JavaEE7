package javaeems.chapter15.library.web;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ejb.*;
import javaeems.chapter15.library.LibraryBean;

import java.util.*;

@Named("addAuthorBean")
@RequestScoped
public class AddAuthorJSFBean {

    @EJB
    LibraryBean librarybean;

    private String lastName;
    private String firstNames;
    private String description;

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setFirstNames(String firstNames) {
        this.firstNames = firstNames;
    }

    public String getFirstNames() {
        return this.firstNames;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void add() {
        List<String> firstNames = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(this.firstNames, " ");
        while (st.hasMoreTokens()) {
            firstNames.add(st.nextToken());
        }

        try {
            this.librarybean.addAuthor(firstNames, this.lastName, this.description);
        } catch (EJBAccessException e) {
            throw new UnauthorizedAccessException("Error adding a new author", librarybean.getClass().getSimpleName(), "addAuthor");
        }
    }

}

package javaeems.chapter15.library.web;

import java.util.ArrayList;
import java.util.List;
import javaeems.chapter15.library.LibraryBean;
import javaeems.chapter15.library.entities.Author;
import javax.ejb.EJB;
import javax.ejb.EJBAccessException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("addBookBean")
@RequestScoped
public class AddBookJSFBean {

    @EJB
    LibraryBean librarybean;

    private String title;
    private String genreName1;
    private String genreName2;
    private Author author;
    private boolean childrensBook = false;
    private List<Integer> ages = new ArrayList<>();
    private int age;

    public AddBookJSFBean() {
        ages.add(2);
        ages.add(3);
        ages.add(4);
        ages.add(5);
        ages.add(6);
        ages.add(7);
        ages.add(8);
        ages.add(9);
        ages.add(10);
        ages.add(11);
        ages.add(12);
        ages.add(13);
        ages.add(14);
        ages.add(15);
        ages.add(16);
    }

    public void add() {
        List<String> genres = new ArrayList<>();
        this.addIfNotEmpty(genreName1, genres);
        this.addIfNotEmpty(genreName2, genres);
        try {
            librarybean.addBook(this.title, this.author.getId(), genres, childrensBook, this.age);
        } catch (EJBAccessException e) {
            throw new UnauthorizedAccessException("Error adding a new book", librarybean.getClass().getSimpleName(), "addBook");
        }
    }

    public int getAuthorId() {
        if (author == null) {
            return 0;
        }
        return this.author.getId();
    }

    public void setAuthorId(int id) {
        this.author = this.librarybean.getAuthorForId(id);
    }

    public void setAuthor(Author a) {
        this.author = a;
    }

    public Author getAuthor() {
        return this.author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private void addIfNotEmpty(String genreName, List<String> names) {
        if (!"".equals(genreName) && !names.contains(genreName)) {
            names.add(genreName);
        }
    }

    public List<String> getGenreNames() {
        List<String> l = new ArrayList<>();
        l.add("");
        l.addAll(librarybean.getGenreNames());
        return l;
    }

    public void setGenreName1(String genreName1) {
        this.genreName1 = genreName1;
    }

    public String getGenreName1() {
        return this.genreName1;
    }

    public void setGenreName2(String genreName2) {
        this.genreName2 = genreName2;
    }

    public String getGenreName2() {
        return this.genreName2;
    }

    public String getTitle() {
        return this.title;
    }

    public boolean getChildrensBook() {
        return this.childrensBook;
    }

    public void setChildrensBook(boolean childrensBook) {
        this.childrensBook = childrensBook;
    }

    public List<Integer> getAges() {
        return this.ages;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }

}

package objectArmy.bookEater.entity.book;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Philip Athanasopoulos
 */
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToMany
    private List<Author> authors;
    private String title;
    private String summary;
    @ManyToMany
    private List<BookCategory> categories;


    public Book() {
    }

    public Book(ArrayList<Author> authors, String title, String summary, ArrayList<BookCategory> categories) {
        this.title = title;
        this.summary = summary;
        this.authors = authors;
        this.categories = categories;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    @Override
    public String toString() {
        return "Book{" +
                "authors=" + authors +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", categories=" + categories +
                '}';
    }

    public void setAuthors(ArrayList<Author> authors) {
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<BookCategory> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<BookCategory> categories) {
        this.categories = categories;
    }
}
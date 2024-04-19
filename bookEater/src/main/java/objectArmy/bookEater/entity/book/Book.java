package objectArmy.bookEater.entity.book;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Philip Athanasopoulos
 */
@Entity
@Table(name = "book")
@NoArgsConstructor
public class Book {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Getter
    @ManyToMany
    private List<Author> authors = new ArrayList<>();
    @Getter
    @Setter
    private String title;
    @Setter
    @Getter
    private String summary;
    @Getter
    @ManyToMany
    private List<BookCategory> categories = new ArrayList<>();


    public Book(ArrayList<Author> authors, String title, String summary, ArrayList<BookCategory> categories) {
        this.title = title;
        this.summary = summary;
        this.authors = authors;
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Book{" + "authors=" + authors + ", title='" + title + '\'' + ", summary='" + summary + '\'' + ", categories=" + categories + '}';
    }


    public void addAuthor(Author author) {
        this.authors.add(author);
    }
}

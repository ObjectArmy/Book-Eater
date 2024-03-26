package objectArmy.bookEater.domain.book;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.stereotype.Component;

/**
 * @author Philip Athanasopoulos
 */
@Entity
@Table(name = "book_category")
public class BookCategory {
    @Id
    private String name;

    public BookCategory() {
    }

    public BookCategory(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BookCategory{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package objectArmy.bookEater.entity.book;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Philip Athanasopoulos
 */
@Entity
@Table(name = "book_category")
@NoArgsConstructor
public class BookCategory {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Setter
    @Getter
    private String name;

    public BookCategory(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BookCategory{" +
                "name='" + name + '\'' +
                '}';
    }
}

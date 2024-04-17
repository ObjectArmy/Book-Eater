package objectArmy.bookEater.entity.book;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Philip Athanasopoulos
 */
@Entity
@NoArgsConstructor
@Table(name = "author")
public class Author {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Getter
    @Setter
    private String name;

    public Author(String name) {
        this.name = name;
    }

}

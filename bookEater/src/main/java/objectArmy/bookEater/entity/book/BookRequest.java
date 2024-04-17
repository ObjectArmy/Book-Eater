package objectArmy.bookEater.entity.book;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import objectArmy.bookEater.entity.user.UserProfile;

/**
 * @author Philip Athanasopoulos
 */
@Entity
@Table(name = "book_request")
@NoArgsConstructor
public class BookRequest {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Getter
    @OneToOne
    private UserProfile requestee;

    @Setter
    @Getter
    @OneToOne
    private BookOffer bookOffer;

    public BookRequest(UserProfile requestee, BookOffer bookOffer) {
        this.requestee = requestee;
        this.bookOffer = bookOffer;
    }

}

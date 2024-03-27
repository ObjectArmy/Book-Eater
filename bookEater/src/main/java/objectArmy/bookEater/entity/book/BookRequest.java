package objectArmy.bookEater.entity.book;

import jakarta.persistence.*;
import objectArmy.bookEater.entity.user.UserProfile;

/**
 * @author Philip Athanasopoulos
 */
@Entity
@Table(name = "book_request")
public class BookRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @OneToOne
    private UserProfile requestee;

    @OneToOne
    private BookOffer bookOffer;

    public BookRequest(UserProfile requestee, BookOffer bookOffer) {
        this.requestee = requestee;
        this.bookOffer = bookOffer;
    }

    public BookRequest() {

    }

    public UserProfile getRequestee() {
        return requestee;
    }

    public void setRequestee(UserProfile requestee) {
        this.requestee = requestee;
    }

    public BookOffer getBookOffer() {
        return bookOffer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBookOffer(BookOffer bookOffer) {
        this.bookOffer = bookOffer;
    }
}

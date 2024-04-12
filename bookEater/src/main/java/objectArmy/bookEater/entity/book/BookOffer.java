package objectArmy.bookEater.entity.book;

import jakarta.persistence.*;
import objectArmy.bookEater.entity.user.UserProfile;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Philip Athanasopoulos
 */
@Entity
@Table(name = "book_offer")
public class BookOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private UserProfile offeror;
    @OneToOne
    private Book offeredBook;
    private String offerDescription;
    @OneToMany
    private List<BookRequest> requests;
    private Date postDate;

    public BookOffer() {
    }

    public BookOffer(UserProfile offeror, Book offeredBook, String offerDescription, Date postDate) {
        this.offeror = offeror;
        this.offeredBook = offeredBook;
        this.offerDescription = offerDescription;
        this.requests = new ArrayList<BookRequest>();
        this.postDate = postDate;
    }

    public UserProfile getOfferor() {
        return offeror;
    }

    public void setOfferor(UserProfile offeror) {
        this.offeror = offeror;
    }

    public Book getOfferedBook() {
        return offeredBook;
    }

    public void setOfferedBook(Book offeredBook) {
        this.offeredBook = offeredBook;
    }

    public String getOfferDescription() {
        return offerDescription;
    }

    public void setOfferDescription(String offerDescription) {
        this.offerDescription = offerDescription;
    }

    public List<BookRequest> getRequests() {
        return requests;
    }

    public void setRequests(ArrayList<BookRequest> requests) {
        this.requests = requests;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getOfferedBookTitle(){
        return offeredBook.getTitle();
    }

    public void setOfferedBookTitle(String title){
        offeredBook.setTitle(title);
    }

    @Override
    public String toString() {
        return "BookOffer{" +
                "offeror=" + offeror +
                ", offeredBook=" + offeredBook +
                ", offerDescription='" + offerDescription + '\'' +
                ", requests=" + requests +
                ", postDate=" + postDate +
                '}';
    }
}

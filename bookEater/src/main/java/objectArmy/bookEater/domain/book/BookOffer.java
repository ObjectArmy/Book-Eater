package objectArmy.bookEater.domain.book;

import objectArmy.bookEater.domain.user.User;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Philip Athanasopoulos
 */
public class BookOffer {
    private User offeror;
    private Book offeredBook;
    private String offerDescription;
    private ArrayList<BookRequest> requests;
    private Date postDate;

    public BookOffer(User offeror, Book offeredBook, String offerDescription, Date postDate) {
        this.offeror = offeror;
        this.offeredBook = offeredBook;
        this.offerDescription = offerDescription;
        this.requests = new ArrayList<BookRequest>();
        this.postDate = postDate;
    }

    public User getOfferor() {
        return offeror;
    }

    public void setOfferor(User offeror) {
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

    public ArrayList<BookRequest> getRequests() {
        return requests;
    }

    public void setRequests(ArrayList<BookRequest> requests) {
        this.requests = requests;
    }

    public Date getPostDate() {
        return postDate;
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

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }
}

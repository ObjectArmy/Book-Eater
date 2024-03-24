package objectArmy.bookEater.domain.user;

import objectArmy.bookEater.domain.book.BookOffer;
import objectArmy.bookEater.domain.book.BookRequest;

import java.util.ArrayList;

/**
 * @author Philip Athanasopoulos
 */
public class UserProfile {
    private User user;
    private ArrayList<BookOffer> bookOffers;
    private ArrayList<BookRequest> outgoingBookRequests;

    public UserProfile(User user) {
        this.user = user;
        this.bookOffers = new ArrayList<BookOffer>();
        this.outgoingBookRequests = new ArrayList<BookRequest>();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<BookOffer> getBookOffers() {
        return bookOffers;
    }

    public void setBookOffers(ArrayList<BookOffer> bookOffers) {
        this.bookOffers = bookOffers;
    }

    public ArrayList<BookRequest> getOutgoingBookRequests() {
        return outgoingBookRequests;
    }

    public void setOutgoingBookRequests(ArrayList<BookRequest> outgoingBookRequests) {
        this.outgoingBookRequests = outgoingBookRequests;
    }

    public void addBookOffer(BookOffer bookOffer) {
        this.bookOffers.add(bookOffer);
    }

    public void removeBookOffer(BookOffer bookOffer) {
        this.bookOffers.remove(bookOffer);
    }

    public void addOutgoingBookRequest(BookRequest bookRequest) {
        this.outgoingBookRequests.add(bookRequest);
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "user=" + user +
                ", bookOffers=" + bookOffers +
                ", outgoingBookRequests=" + outgoingBookRequests +
                '}';
    }

    public void removeOutgoingBookRequest(BookRequest bookRequest) {
        this.outgoingBookRequests.remove(bookRequest);
    }
}

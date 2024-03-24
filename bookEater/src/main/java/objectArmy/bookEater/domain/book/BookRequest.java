package objectArmy.bookEater.domain.book;

import objectArmy.bookEater.domain.user.User;

/**
 * @author Philip Athanasopoulos
 */
public class BookRequest {
    private User requestee;
    private BookOffer bookOffer;

    public BookRequest(User requestee, BookOffer bookOffer) {
        this.requestee = requestee;
        this.bookOffer = bookOffer;
    }

    public User getRequestee() {
        return requestee;
    }

    public void setRequestee(User requestee) {
        this.requestee = requestee;
    }

    public BookOffer getBookOffer() {
        return bookOffer;
    }

    public void setBookOffer(BookOffer bookOffer) {
        this.bookOffer = bookOffer;
    }
}

package objectArmy.bookEater.service;

import objectArmy.bookEater.dao.BookOfferRepository;
import objectArmy.bookEater.entity.book.BookOffer;
import objectArmy.bookEater.entity.book.BookRequest;
import objectArmy.bookEater.entity.user.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Philip Athanasopoulos
 */
@Service
public class BookOfferService {
    @Autowired
    BookOfferRepository bookOfferRepository;

    @Autowired
    BookService bookService;

    @Lazy
    @Autowired
    UserService userService;

    @Autowired
    BookRequestService bookRequestService;

    public void saveBookOffer(BookOffer bookOffer) {
        bookService.saveBook(bookOffer.getOfferedBook());
        bookOfferRepository.save(bookOffer);
    }

    public List<BookOffer> getAllBookOffers() {
        return bookOfferRepository.findAll();
    }

    public void addBookRequest(Long userId, Long bookOfferId) {
        UserProfile user = userService.getUserById(userId);
        BookOffer bookOffer = getBookOfferById(bookOfferId);
        BookRequest request = new BookRequest(user, bookOffer);
        bookRequestService.saveBookRequest(request);

        //save request to the user
        user.addOutgoingBookRequest(request);
        userService.saveUser(user);

        //save request to the offer
        bookOffer.addRequest(request);
        this.saveBookOffer(bookOffer);

    }

    public BookOffer getBookOfferById(Long id) {
        return bookOfferRepository.findBookOfferById(id);
    }

    public void deleteBookOfferById(Long id) {
        bookOfferRepository.deleteById(id);
    }
}

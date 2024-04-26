package objectArmy.bookEater.service;

import lombok.extern.slf4j.Slf4j;
import objectArmy.bookEater.dao.BookOfferRepository;
import objectArmy.bookEater.entity.book.BookOffer;
import objectArmy.bookEater.entity.book.BookRequest;
import objectArmy.bookEater.entity.user.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Philip Athanasopoulos
 */
@Slf4j
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

    @Transactional
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

    @Transactional
    public void deleteBookOfferById(Long id) {

        BookOffer offer = bookOfferRepository.findBookOfferById(id);
        UserProfile offeror = offer.getOfferor();


        offeror.removeBookOffer(offer);
        userService.saveUser(offeror);

        //remove requests associated with the offer
        for (BookRequest request : offer.getRequests()) {
            UserProfile requestor = request.getRequestee();
            requestor.removeOutgoingBookRequest(request);
            userService.saveUser(requestor);
            bookRequestService.deleteBookRequest(request);
        }

        //remove offer
        bookOfferRepository.delete(offer);
    }
    // Find by bookTitle
    public List<BookOffer> searchByTitle(String userQuery){
        return this.bookOfferRepository.findByTitle(userQuery);
    }
}

package objectArmy.bookEater.service;

import lombok.extern.slf4j.Slf4j;
import objectArmy.bookEater.dao.BookOfferRepository;
import objectArmy.bookEater.entity.book.BookCategory;
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
@Service
@Transactional
public class BookOfferService {
    BookOfferRepository bookOfferRepository;

    BookService bookService;

    UserService userService;

    BookRequestService bookRequestService;

    //Circular dependency is resolved by using @Lazy annotation
    @Autowired
    public BookOfferService(BookOfferRepository bookOfferRepository, BookService bookService, @Lazy UserService userService, @Lazy BookRequestService bookRequestService) {
        this.bookOfferRepository = bookOfferRepository;
        this.bookService = bookService;
        this.userService = userService;
        this.bookRequestService = bookRequestService;
    }


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
    public List<BookOffer> searchByTitleApproximately(String userQuery) {
        return this.bookOfferRepository.findByTitleApproximately(userQuery);
    }

    // Find by bookAuthor
    public List<BookOffer> searchByAuthorApproximately(String userQuery) {
        return this.bookOfferRepository.findByAuthorApproximately(userQuery);
    }

    // Find by bookTitle
    public List<BookOffer> searchByTitleExact(String userQuery) {
        return this.bookOfferRepository.findByTitleExact(userQuery);
    }

    // Find by bookAuthor
    public List<BookOffer> searchByAuthorExact(String userQuery) {
        return this.bookOfferRepository.findByAuthorExact(userQuery);
    }

    public List<BookOffer> getOffersByCategory(BookCategory favoriteCategory) {
        return bookOfferRepository.findByOfferedBookCategory(favoriteCategory);
    }

    public List<BookOffer> getAllBookOffersExceptFor(Long id) {
        return bookOfferRepository.findAllExceptFor(id);
    }
}

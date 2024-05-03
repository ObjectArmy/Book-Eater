package objectArmy.bookEater.service;

import objectArmy.bookEater.dao.BookOfferRepository;
import objectArmy.bookEater.dao.BookRequestRepository;
import objectArmy.bookEater.entity.book.Book;
import objectArmy.bookEater.entity.book.BookOffer;
import objectArmy.bookEater.entity.book.BookRequest;
import objectArmy.bookEater.entity.user.UserProfile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Philip Athanasopoulos
 */
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class BookOfferServiceTest {
    @Autowired
    UserService userService;
    @Autowired
    BookOfferService bookOfferService;
    @Autowired
    BookOfferRepository bookOfferRepository;
    @Autowired
    BookRequestRepository bookRequestRepository;
    @Autowired
    BookRequestService bookRequestService;
    BookOffer bookOffer;
    private UserProfile user1;
    private UserProfile user2;

    @BeforeEach
    public void setUp() {
        // Create a new UserProfile instance
        user1 = new UserProfile("aFirstName", "aLastName", new Date(), 20, "anemail@gmail.com", "password");
        user2 = new UserProfile("aFirstName", "aLastName", new Date(), 20, "anotheremail@gmail.com", "password");

        userService.saveUser(user1);
        userService.saveUser(user2);

        Book offeredBook = new Book(new ArrayList<>(), "Title", "Summary", new ArrayList<>());
        bookOffer = new BookOffer(user1, offeredBook, "Description", new Date());
    }

    @Test
    @Transactional
    void saveBookOffer() {
        bookOfferService.saveBookOffer(bookOffer);
        assertEquals(1, bookOffer.getOfferor().getId());
        assertEquals(1, bookOffer.getOfferedBook().getId());
    }

    @Test
    @Transactional
    void getAllBookOffers() {
        bookOfferService.saveBookOffer(bookOffer);
        assertNotNull(bookOfferService.getAllBookOffers());
        assertEquals(1, bookOfferService.getAllBookOffers().size());
        assertEquals(bookOffer, bookOfferService.getAllBookOffers().get(0));
    }

    @Test
    @Transactional
    void getBookOfferById() {
        bookOfferService.saveBookOffer(bookOffer);
        assertEquals(bookOffer, bookOfferService.getBookOfferById(bookOffer.getId()));
    }

    @Test
    void deleteBookOfferById() {
        bookOfferService.saveBookOffer(bookOffer);
        bookOfferService.deleteBookOfferById(bookOffer.getId());
        assertEquals(0, bookOfferService.getAllBookOffers().size());
        assertEquals(0, bookRequestRepository.findAll().size());
    }

    @Test
    @Transactional
    void deleteBookOffer() {
        bookOfferService.saveBookOffer(bookOffer);
        bookRequestService.saveBookRequest(new BookRequest(user2, bookOffer));

        bookOfferService.deleteBookOffer(bookOffer);
        assertEquals(0, bookOfferService.getAllBookOffers().size());
        assertEquals(0, bookRequestRepository.findAll().size());
    }

    @Test
    void searchByTitleApproximately() {
        bookOfferService.saveBookOffer(bookOffer);
    }

    @Test
    void searchByAuthorApproximately() {
    }

    @Test
    void searchByTitleExact() {
    }

    @Test
    void searchByAuthorExact() {
    }

    @Test
    void getOffersByCategory() {
    }

    @Test
    void getAllBookOffersExceptFor() {
    }
}

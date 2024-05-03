package objectArmy.bookEater.service;

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
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class BookRequestServiceTest {
    @Autowired
    UserService userService;
    @Autowired
    BookRequestService bookRequestService;
    @Autowired
    BookOfferService bookOfferService;
    @Autowired
    BookRequestRepository bookRequestRepository;
    private UserProfile user1;
    private UserProfile user2;
    private BookOffer bookOffer;

    @BeforeEach
    void setUp() {
        user1 = new UserProfile("aFirstName", "aLastName", new Date(), 20, "someone@gmail.com", "password");
        user2 = new UserProfile("aFirstName", "aLastName", new Date(), 20, "someoneElse@gmail.com", "password");
        userService.saveUser(user1);
        userService.saveUser(user2);

        Book offeredBook = new Book(new ArrayList<>(), "Title", "Summary", new ArrayList<>());
        bookOffer = new BookOffer(user1, offeredBook, "Description", new Date());
        bookOfferService.saveBookOffer(bookOffer);
    }

    @Test
    @Transactional
    void saveBookRequest() {
        BookRequest bookRequest = new BookRequest(user2, bookOffer);
        bookRequestService.saveBookRequest(bookRequest);
        assertEquals(user1.getIncomingBookRequests().get(0), bookRequest);
        assertEquals(user2.getOutgoingBookRequests().get(0), bookRequest);
        assertEquals(bookOffer.getRequests().get(0), bookRequest);

    }

    @Test
    @Transactional
    void deleteBookRequest() {
        BookRequest bookRequest = new BookRequest(user2, bookOffer);
        bookRequestService.saveBookRequest(bookRequest);
        bookRequestService.deleteBookRequest(bookRequest);
        assertTrue(user1.getIncomingBookRequests().isEmpty());
        assertTrue(user2.getOutgoingBookRequests().isEmpty());
        assertTrue(bookOffer.getRequests().isEmpty());
    }

    @Test
    @Transactional
    void getBookRequestById() {
        BookRequest bookRequest = new BookRequest(user2, bookOffer);
        bookRequestService.saveBookRequest(bookRequest);
        BookRequest bookRequest1 = bookRequestService.getBookRequestById(1L);
        assertEquals(bookRequest, bookRequest1);

    }

    @Test
    @Transactional
    void deleteBookRequestById() {
        BookRequest bookRequest = new BookRequest(user2, bookOffer);
        bookRequestService.saveBookRequest(bookRequest);
        bookRequestService.deleteBookRequestById(1L);
        assertTrue(user1.getIncomingBookRequests().isEmpty());
        assertTrue(user2.getOutgoingBookRequests().isEmpty());
        assertTrue(bookOffer.getRequests().isEmpty());
    }

    @Test
    void removeBookRequestsForOffer() {
    }

    @Test
    void removeRequest() {
    }

    @Test
    void acceptRequest() {
    }
}
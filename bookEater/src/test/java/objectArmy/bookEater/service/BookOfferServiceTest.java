package objectArmy.bookEater.service;

import objectArmy.bookEater.entity.book.Book;
import objectArmy.bookEater.entity.book.BookOffer;
import objectArmy.bookEater.entity.user.UserProfile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    String green = "\033[0;32m"; // ANSI green
    String reset = "\033[0m"; // ANSI reset


    private UserProfile user1;
    private UserProfile user2;
    private BookOffer bookOffer;


    @BeforeEach
    public void setUp() {
        // Create a new UserProfile instance
        user1 = new UserProfile();
        user1.setFirstName("Book");
        user1.setLastName("Offeror");
        user1.setPassword("password");
        user2 = new UserProfile();
        user2.setFirstName("Book");
        user2.setLastName("Requestor");
        user2.setPassword("password");
        // Set other necessary properties...

        // Save the UserProfile instance
        userService.saveUser(user1);
        userService.saveUser(user2);

        // Create a new BookOffer instance
        BookOffer newOffer = new BookOffer();
        newOffer.setOfferedBook(new Book());
        newOffer.setOfferor(user1);
        // Set other necessary properties...

        // Save the BookOffer instance
        userService.addBookOfferToUser(user1, newOffer);
        System.out.println(user1.getFullName() + " offers a book :" + user1.getBookOffers().toString());
    }

    @Test
    public void testRemoveBookOffer() {
        //user2 requests the book from user1
        Long offerId = user1.getBookOffers().get(0).getId();
        bookOfferService.addBookRequest(user2.getId(), offerId);
        bookOfferService.deleteBookOfferById(offerId);

        user1 = userService.getUserById(user1.getId());
        user2 = userService.getUserById(user2.getId());

        assertNull(bookOfferService.getBookOfferById(offerId));
        assertNull(user1.getBookOffers());
        assertTrue(user2.getOutgoingBookRequests().isEmpty());
    }

}

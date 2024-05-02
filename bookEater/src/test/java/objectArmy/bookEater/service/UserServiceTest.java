package objectArmy.bookEater.service;

import objectArmy.bookEater.entity.book.Book;
import objectArmy.bookEater.entity.book.BookOffer;
import objectArmy.bookEater.entity.user.UserProfile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserServiceTest {
    @Autowired
    private UserService userService;
    private UserProfile user1;
    @Autowired
    BookOfferService bookOfferService;
    // add something else...

    @BeforeEach
    public void setUp(){
        user1 = new UserProfile("aFirstName", "aLastName", new Date(), 20, "someone@gmail.com", "password");
        userService.saveUser(user1);
    }

    @Test
    public void testDeleteUser(){
        Long user1ID = user1.getId();
        userService.deleteUser(user1);
        assertNull(userService.getUserById(user1ID));
    }

    @Test
    public void testSaveUser(){
        assertNotNull(userService.getUserById(user1.getId()));
    }

    @Test
    public void testAddBookOfferToUser(){
        BookOffer bookOffer = new BookOffer(user1, new Book(), "aBook", new Date());
        userService.addBookOfferToUser(user1, bookOffer);
        assertNotNull(bookOfferService.getBookOfferById(bookOffer.getId()));
    }

}

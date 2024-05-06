package objectArmy.bookEater.service;

import objectArmy.bookEater.entity.user.UserProfile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserServiceTest {
    @Autowired
    BookOfferService bookOfferService;
    @Autowired
    private UserService userService;
    private UserProfile user1;

    @BeforeEach
    public void setUp() {
        user1 = new UserProfile("aFirstName", "aLastName",LocalDate.of(2002,11,4), "someone@gmail.com", "password");
        userService.saveUser(user1);
    }

    @Test
    void saveUser() {
        assertNotNull(userService.getUserById(user1.getId()));
    }

    @Test
    void deleteUser() {
        Long user1ID = user1.getId();
        userService.deleteUser(user1);
        assertNull(userService.getUserById(user1ID));
    }

    @Test
    void loadUserByUsername() {
    }

    @Test
    @Transactional
    void getUserById() {
        assertEquals(user1, userService.getUserById(1));
    }


    @Test
    void updateUser() {
        //TODO
    }
}

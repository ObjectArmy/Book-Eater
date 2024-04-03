package objectArmy.bookEater.controller;

import objectArmy.bookEater.entity.user.UserProfile;
import objectArmy.bookEater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Philip Athanasopoulos
 */
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userProfileRepository) {
        this.userService = userProfileRepository;
    }

    @GetMapping("/users")
    public List<UserProfile> getUsers(){
        return userService.getUsers();
    }

}

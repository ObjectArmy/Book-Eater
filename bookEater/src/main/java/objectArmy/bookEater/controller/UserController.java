package objectArmy.bookEater.controller;

import objectArmy.bookEater.dao.UserProfileRepository;
import objectArmy.bookEater.entity.user.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Philip Athanasopoulos
 */
@RestController
public class UserController {
    private final UserProfileRepository userProfileRepository;

    @Autowired
    public UserController(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @GetMapping("/users")
    public List<UserProfile> getUsers(){
        return userProfileRepository.findAll();
    }

}

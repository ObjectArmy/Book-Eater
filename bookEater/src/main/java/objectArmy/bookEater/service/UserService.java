package objectArmy.bookEater.service;

import objectArmy.bookEater.dao.UserProfileRepository;
import objectArmy.bookEater.entity.user.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Philip Athanasopoulos
 */
@Service
public class UserService {
    private final UserProfileRepository userProfileRepository;

    @Autowired
    public UserService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public List<UserProfile> getUsers() {
        return userProfileRepository.findAll();
    }
}

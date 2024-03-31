package objectArmy.bookEater.service;

import objectArmy.bookEater.dao.UserProfileRepository;
import objectArmy.bookEater.entity.user.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Philip Athanasopoulos
 */
@Service
public class UserService {
    private final UserProfileRepository userProfileRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserProfileRepository userProfileRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userProfileRepository = userProfileRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserProfile> getUsers() {
        return userProfileRepository.findAll();
    }

    public void saveUser(UserProfile userToAdd) {
        String hashedPassword = passwordEncoder.encode(userToAdd.getPassword());
        userToAdd.setPassword(hashedPassword);
        userProfileRepository.save(userToAdd);
    }

    public void deleteUser(UserProfile userToDelete) {
        userProfileRepository.delete(userToDelete);
    }
}

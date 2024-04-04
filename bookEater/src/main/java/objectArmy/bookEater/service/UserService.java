package objectArmy.bookEater.service;

import objectArmy.bookEater.dao.UserProfileRepository;
import objectArmy.bookEater.entity.user.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Philip Athanasopoulos
 */
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserProfile user = userProfileRepository.findUserByEmail(email);
        if (user == null) throw new UsernameNotFoundException("User not found");
        return user;
    }
}

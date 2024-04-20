package objectArmy.bookEater.service;

import objectArmy.bookEater.dao.UserProfileRepository;
import objectArmy.bookEater.entity.book.BookOffer;
import objectArmy.bookEater.entity.user.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Philip Athanasopoulos
 */
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private BookOfferService bookOfferService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<UserProfile> getUsers() {
        return userProfileRepository.findAll();
    }

    public void saveUser(UserProfile userToAdd) {
        UserProfile existingUser = userProfileRepository.findUserById(userToAdd.getId());

        if (existingUser == null) {
            String hashedPassword = passwordEncoder.encode(userToAdd.getPassword());
            userToAdd.setPassword(hashedPassword);
        }

        userProfileRepository.save(userToAdd);
    }

    public void addBookOfferToUser(UserProfile user, BookOffer bookOffer) {
        bookOffer.setOfferor(user);
        bookOffer.setPostDate(new Date());
        bookOfferService.saveBookOffer(bookOffer);
        user.addBookOffer(bookOffer);
        userProfileRepository.save(user);
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

    public UserProfile getUserById(long id) {
        return userProfileRepository.findUserById(id);
    }

    public UserProfile updateUser(UserProfile newUserProfile, Long id) {
        UserProfile user = userProfileRepository.findUserById(id);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        if (newUserProfile.getFirstName() != null && !newUserProfile.getFirstName().equals(user.getFirstName())) {
            user.setFirstName(newUserProfile.getFirstName());
        }

        if (newUserProfile.getLastName() != null && !newUserProfile.getLastName().equals(user.getLastName())) {
            user.setLastName(newUserProfile.getLastName());
        }

        if (newUserProfile.getEmail() != null && !newUserProfile.getEmail().equals(user.getEmail())) {
            user.setEmail(newUserProfile.getEmail());
        }

//        if (newUserProfile.getPassword() != null && !passwordEncoder.matches(newUserProfile.getPassword(), user.getPassword())) {
//            user.setPassword(passwordEncoder.encode(newUserProfile.getPassword()));
//        }

        if (newUserProfile.getBio() != null && !newUserProfile.getBio().equals(user.getBio())) {
            user.setBio(newUserProfile.getBio());
        }

        userProfileRepository.save(user);
        return user;
    }
}

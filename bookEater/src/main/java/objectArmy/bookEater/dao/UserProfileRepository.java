package objectArmy.bookEater.dao;

import objectArmy.bookEater.entity.user.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    UserProfile findUserByEmail(String email);
    UserProfile findUserById(long id);
}

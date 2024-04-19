package objectArmy.bookEater.entity.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import objectArmy.bookEater.entity.book.BookCategory;
import objectArmy.bookEater.entity.book.BookOffer;
import objectArmy.bookEater.entity.book.BookRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author Philip Athanasopoulos
 */

@Slf4j
@Getter
@Entity
@Table(name = "user_profile")
public class UserProfile implements UserDetails {

    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String firstName;
    @Setter
    private String lastName;
    @Setter
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateOfBirth;
    @Setter
    @Column(unique = true)
    private String email;
    @Setter
    private String password;
    @Setter
    private String bio;
    @ManyToMany
    private List<BookCategory> favoriteCategories;
    @Setter
    @OneToMany
    private List<BookOffer> bookOffers;
    @Setter
    @OneToMany
    private List<BookRequest> outgoingBookRequests;

    public UserProfile() {
        this.bookOffers = new ArrayList<>();
        this.outgoingBookRequests = new ArrayList<>();
    }

    public UserProfile(String firstName, String lastName, Date dateOfBirth, int age, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
        this.bookOffers = new ArrayList<>();
        this.outgoingBookRequests = new ArrayList<>();
        this.favoriteCategories = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "UserProfile{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", dateOfBirth=" + dateOfBirth + ", email='" + email + '\'' + '}';
    }

    public void addBookOffer(BookOffer bookOffer) {
        this.bookOffers.add(bookOffer);
    }

    public void addOutgoingBookRequest(BookRequest bookRequest) {
        this.outgoingBookRequests.add(bookRequest);
    }

    public void removeBookOffer(BookOffer bookOffer) {
        this.bookOffers.remove(bookOffer);
    }

    public void removeOutgoingBookRequest(BookRequest bookRequest) {
        if (this.outgoingBookRequests.remove(bookRequest)) log.info("Request removed from" + this.getFullName());
        else log.error("Request not found in " + this.getFullName());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public List<BookRequest> getIncomingBookRequests() {
        List<BookRequest> incomingBookRequests = new ArrayList<>();
        for (BookOffer bookOffer : this.bookOffers) {
            incomingBookRequests.addAll(bookOffer.getRequests());
        }
        return incomingBookRequests;
    }
}

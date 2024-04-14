package objectArmy.bookEater.entity.user;

import jakarta.persistence.*;
import objectArmy.bookEater.entity.book.BookCategory;
import objectArmy.bookEater.entity.book.BookOffer;
import objectArmy.bookEater.entity.book.BookRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author Philip Athanasopoulos
 */

@Entity
@Table(name = "user_profile")
public class UserProfile implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateOfBirth;
    @Column(unique = true)
    private String email;
    private String password;
    private String bio;
    @ManyToMany
    private List<BookCategory> favoriteCategories;
    @OneToMany
    private List<BookOffer> bookOffers;
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

    @Transactional
    public List<BookCategory> getFavoriteCategories() {
        return favoriteCategories;
    }

    public void setFavoriteCategories(List<BookCategory> favoriteCategories) {
        this.favoriteCategories = favoriteCategories;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "UserProfile{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", dateOfBirth=" + dateOfBirth + ", email='" + email + '\'' + '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Transactional
    public List<BookOffer> getBookOffers() {
        return bookOffers;
    }

    public void setBookOffers(List<BookOffer> bookOffers) {
        this.bookOffers = bookOffers;
    }

    public void setBookOffers(ArrayList<BookOffer> bookOffers) {
        this.bookOffers = bookOffers;
    }

    @Transactional
    public List<BookRequest> getOutgoingBookRequests() {
        return outgoingBookRequests;
    }

    public void setOutgoingBookRequests(List<BookRequest> outgoingBookRequests) {
        this.outgoingBookRequests = outgoingBookRequests;
    }

    public void setOutgoingBookRequests(ArrayList<BookRequest> outgoingBookRequests) {
        this.outgoingBookRequests = outgoingBookRequests;
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
        this.outgoingBookRequests.remove(bookRequest);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName(){
        return this.firstName + " " + this.lastName;
    }
}

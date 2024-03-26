package objectArmy.bookEater.domain.user;

import jakarta.persistence.*;
import objectArmy.bookEater.domain.book.BookOffer;
import objectArmy.bookEater.domain.book.BookRequest;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Philip Athanasopoulos
 */
@Entity
@Table(name = "user")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private String firstName;
    private String lastName;
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    private int age;
    private String email;
    @OneToMany
    private ArrayList<BookOffer> bookOffers;
    @OneToMany
    private ArrayList<BookRequest> outgoingBookRequests;

    public UserProfile() {
        this.bookOffers = new ArrayList<>();
        this.outgoingBookRequests = new ArrayList<>();
    }

    public UserProfile(String firstName, String lastName, Date dateOfBirth, int age, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.email = email;
        this.bookOffers = new ArrayList<>();
        this.outgoingBookRequests = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "UserProfile{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", dateOfBirth=" + dateOfBirth + ", age=" + age + ", email='" + email + '\'' + '}';
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

    public ArrayList<BookOffer> getBookOffers() {
        return bookOffers;
    }

    public void setBookOffers(ArrayList<BookOffer> bookOffers) {
        this.bookOffers = bookOffers;
    }

    public ArrayList<BookRequest> getOutgoingBookRequests() {
        return outgoingBookRequests;
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


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

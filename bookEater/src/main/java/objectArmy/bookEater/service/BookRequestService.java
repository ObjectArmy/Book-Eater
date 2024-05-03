package objectArmy.bookEater.service;

import objectArmy.bookEater.dao.BookRequestRepository;
import objectArmy.bookEater.entity.book.BookOffer;
import objectArmy.bookEater.entity.book.BookRequest;
import objectArmy.bookEater.entity.user.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Philip Athanasopoulos
 */

@Service
@Transactional
public class BookRequestService {
    private final BookRequestRepository bookRequestRepository;
    private final UserService userProfileService;
    private final BookOfferService bookOfferService;

    @Autowired
    public BookRequestService(BookRequestRepository bookRequestRepository, UserService userProfileService, BookOfferService bookOfferService) {
        this.bookRequestRepository = bookRequestRepository;
        this.userProfileService = userProfileService;
        this.bookOfferService = bookOfferService;
    }

    public void saveBookRequest(BookRequest bookRequest) {
        BookOffer offer = bookOfferService.getBookOfferById(bookRequest.getBookOffer().getId());
        UserProfile requestee = userProfileService.getUserById(bookRequest.getRequestee().getId());
        offer.addRequest(bookRequest);
        requestee.addOutgoingBookRequest(bookRequest);
        bookRequestRepository.save(bookRequest);
    }

    public void deleteBookRequest(BookRequest request) {
        UserProfile requestee = request.getRequestee();
        UserProfile offeror = request.getBookOffer().getOfferor();
        requestee.removeOutgoingBookRequest(request);
        offeror.removeIncomingBookRequest(request);
        request.getBookOffer().removeRequest(request);
        bookRequestRepository.delete(request);
    }

    public BookRequest getBookRequestById(Long id) {
        return bookRequestRepository.findById(id).orElse(null);
    }

    public void deleteBookRequestById(Long id) {
        deleteBookRequest(bookRequestRepository.getReferenceById(id));
    }

    public void acceptRequest(Long requestId) {
        BookRequest request = getBookRequestById(requestId);
        BookOffer offer = request.getBookOffer();
        UserProfile acceptedUser = request.getRequestee();
        acceptedUser.sentNotification("Your request for " + offer.getOfferedBook().getTitle() + " has been accepted!");

        List<BookRequest> requestsToRemove = bookRequestRepository.findAllByBookOfferId(offer.getId());
        for (BookRequest bookRequest : requestsToRemove) {
            if (bookRequest.getRequestee().equals(acceptedUser)) {
                requestsToRemove.remove(bookRequest);
                break;
            }
        }

        for (BookRequest bookRequest : requestsToRemove) {
            offer.removeRequest(bookRequest);
            bookRequest.getRequestee().removeOutgoingBookRequest(bookRequest);
        }

        bookRequestRepository.deleteAll(requestsToRemove);
    }

    public void deleteAllBookRequestsForOffer(BookOffer offer) {
        UserProfile offeror = offer.getOfferor();
        for (BookRequest request : offer.getRequests()) {
            request.getRequestee().removeOutgoingBookRequest(request);
            offeror.removeOutgoingBookRequest(request);
        }
        bookRequestRepository.deleteAll(offer.getRequests());

    }
}

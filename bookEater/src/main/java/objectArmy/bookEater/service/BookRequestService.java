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


    BookRequestRepository bookRequestRepository;

    @Autowired
    public BookRequestService(BookRequestRepository bookRequestRepository) {
        this.bookRequestRepository = bookRequestRepository;
    }

    public void saveBookRequest(BookRequest bookRequest) {
        bookRequestRepository.save(bookRequest);
    }

    public void deleteBookRequest(BookRequest request) {
        bookRequestRepository.delete(request);
    }

    public BookRequest getBookRequestById(Long id) {
        return bookRequestRepository.findById(id).orElse(null);
    }

    public void deleteBookRequestById(Long id) {
        bookRequestRepository.deleteById(id);
    }

    public void removeBookRequestsForOffer(BookOffer offer) {
        List<BookRequest> requests = bookRequestRepository.findByBookOffer(offer);
        for (BookRequest request : requests) {
            offer.removeRequest(request);
            request.getRequestee().removeOutgoingBookRequest(request);
            deleteBookRequest(request);
        }
    }

    public void removeRequest(BookRequest request) {
        request.getRequestee().removeOutgoingBookRequest(request);
        request.getBookOffer().removeRequest(request);
        deleteBookRequest(request);
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
}

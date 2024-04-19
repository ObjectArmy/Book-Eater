package objectArmy.bookEater.service;

import objectArmy.bookEater.dao.BookRequestRepository;
import objectArmy.bookEater.entity.book.BookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Philip Athanasopoulos
 */

@Service
public class BookRequestService {


    @Autowired
    BookRequestRepository bookRequestRepository;


    public void saveBookRequest(BookRequest bookRequest) {
        bookRequestRepository.save(bookRequest);
    }

    public void deleteBookRequest(BookRequest request) {
        bookRequestRepository.delete(request);
    }

    public List<BookRequest> getUsersReceivedRequests(Long id) {
        return bookRequestRepository.findByRequesteeId(id);
    }

}

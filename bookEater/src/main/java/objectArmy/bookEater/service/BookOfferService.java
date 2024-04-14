package objectArmy.bookEater.service;

import objectArmy.bookEater.dao.BookOfferRepository;
import objectArmy.bookEater.entity.book.BookOffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Philip Athanasopoulos
 */
@Service
public class BookOfferService {
    @Autowired
    BookOfferRepository bookOfferRepository;

    @Autowired
    BookService bookService;

    public void saveBookOffer(BookOffer bookOffer) {
        bookService.saveBook(bookOffer.getOfferedBook());
        bookOfferRepository.save(bookOffer);
    }

    public List<BookOffer> getAllBookOffers(){
        return bookOfferRepository.findAll();
    }

    public void deleteBookOfferById(Long id) {
        bookOfferRepository.deleteById(id);
    }
}

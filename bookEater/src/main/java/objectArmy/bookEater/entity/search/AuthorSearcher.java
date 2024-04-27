package objectArmy.bookEater.entity.search;

import objectArmy.bookEater.entity.book.BookOffer;
import objectArmy.bookEater.service.BookOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Philip Athanasopoulos
 */
@Component
public class AuthorSearcher implements Searcher {
    @Autowired
    BookOfferService bookOfferService;


    @Override
    public List<BookOffer> searchApproximately(String query) {
        return null;
    }

    @Override
    public List<BookOffer> searchExact(String query) {
        return null;
    }
}

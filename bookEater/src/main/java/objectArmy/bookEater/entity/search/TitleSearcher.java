package objectArmy.bookEater.entity.search;

import objectArmy.bookEater.entity.book.BookOffer;
import objectArmy.bookEater.service.BookOfferService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TitleSearcher implements Searcher {

    @Autowired
    private BookOfferService bookOfferService;

    @Override
    public List<BookOffer> search(String queryBookTitle) {
        /* For each book
         *  if queryBookTile = bookTitle
         *  Add it to the list */
        return List.of();
    }
}

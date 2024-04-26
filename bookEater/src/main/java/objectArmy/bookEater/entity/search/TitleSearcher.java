package objectArmy.bookEater.entity.search;

import objectArmy.bookEater.entity.book.BookOffer;
import objectArmy.bookEater.service.BookOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class TitleSearcher implements Searcher {


    @Autowired
    private BookOfferService bookOfferService;



    @Override
    public List<BookOffer> search(String queryBookTitle) {
        return bookOfferService.searchByTitle(queryBookTitle);
    }
}

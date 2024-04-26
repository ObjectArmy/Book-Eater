package objectArmy.bookEater.entity.search;

import objectArmy.bookEater.entity.book.BookOffer;
import objectArmy.bookEater.service.BookOfferService;
import objectArmy.bookEater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface Searcher {

    List<BookOffer> search(String query);

}

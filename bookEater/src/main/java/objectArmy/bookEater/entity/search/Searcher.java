package objectArmy.bookEater.entity.search;

import objectArmy.bookEater.entity.book.BookOffer;

import java.util.List;

public interface Searcher {

    List<BookOffer> search(String query);

}

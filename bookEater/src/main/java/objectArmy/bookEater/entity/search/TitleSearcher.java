package objectArmy.bookEater.entity.search;

import objectArmy.bookEater.entity.book.BookOffer;

import java.util.List;

public class TitleSearcher implements Searcher{
    @Override
    public List<BookOffer> search(String queryBookTitle) {
        /* For each book
        *  if queryBookTile = bookTitle
        *  Add it to the list */
        return List.of();
    }
}

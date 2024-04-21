package objectArmy.bookEater.dao;

import objectArmy.bookEater.entity.book.BookOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Philip Athanasopoulos
 */
@Repository
public interface BookOfferRepository extends JpaRepository<BookOffer, Long> {
    BookOffer findBookOfferById(Long id);

}

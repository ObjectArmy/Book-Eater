package objectArmy.bookEater.dao;

import objectArmy.bookEater.entity.book.BookOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Philip Athanasopoulos
 */
@Repository
public interface BookOfferRepository extends JpaRepository<BookOffer, Long> {
    BookOffer findBookOfferById(Long id);

    @Query(value = "select offer from BookOffer offer join offer.offeredBook book where book.title like %:userQuery%")
    List<BookOffer> findByTitle(@Param("userQuery") String userQuery);

    @Query(value = "select offer from BookOffer offer join offer.offeredBook book where book.author like %:userQuery%")
    List<BookOffer> findByAuthor(@Param("userQuery") String userQuery);

}

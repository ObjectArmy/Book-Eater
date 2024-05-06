package objectArmy.bookEater.repository;

import objectArmy.bookEater.entity.book.BookCategory;
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
    List<BookOffer> findByTitleApproximately(@Param("userQuery") String userQuery);

    @Query(value = "select offer from BookOffer offer join offer.offeredBook book join book.authors author where author.name like %:userQuery%")
    List<BookOffer> findByAuthorApproximately(@Param("userQuery") String userQuery);

    @Query(value = "select offer from BookOffer offer join offer.offeredBook book where book.title = :userQuery")
    List<BookOffer> findByTitleExact(@Param("userQuery") String userQuery);

    @Query(value = "select offer from BookOffer offer join offer.offeredBook book join book.authors author where author.name = :userQuery")
    List<BookOffer> findByAuthorExact(@Param("userQuery") String userQuery);

    @Query(value = "select offer from BookOffer offer join offer.offeredBook book join book.categories category where category = :favoriteCategory")
    List<BookOffer> findByOfferedBookCategory(BookCategory favoriteCategory);

    @Query(value = "select offer from BookOffer offer where not offer.offeror.id = :id")
    List<BookOffer> findAllExceptFor(Long id);

}

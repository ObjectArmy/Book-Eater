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

    @Query(value = "select * from book tempBook where tempBook.title like %:userQuery%", nativeQuery = true)
    List<BookOffer> findByTitle(@Param("userQuery") String userQuery);

}

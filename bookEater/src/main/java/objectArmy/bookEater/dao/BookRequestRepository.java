package objectArmy.bookEater.dao;

import objectArmy.bookEater.entity.book.BookRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Philip Athanasopoulos
 */
@Repository
public interface BookRequestRepository extends JpaRepository<BookRequest, Long> {

}

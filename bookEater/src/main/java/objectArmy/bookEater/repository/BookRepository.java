package objectArmy.bookEater.repository;

import objectArmy.bookEater.entity.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Philip Athanasopoulos
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}

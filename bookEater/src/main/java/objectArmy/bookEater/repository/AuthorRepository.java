package objectArmy.bookEater.repository;

import objectArmy.bookEater.entity.book.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author findByName(String name);

    Author getByName(String john);
}

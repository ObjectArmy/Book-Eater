package objectArmy.bookEater.dao;

import objectArmy.bookEater.entity.book.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCategoryRepository extends JpaRepository<BookCategory, Long> {

    BookCategory findByName(String name);
}

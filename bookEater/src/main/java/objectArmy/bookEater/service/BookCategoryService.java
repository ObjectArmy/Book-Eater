package objectArmy.bookEater.service;

import objectArmy.bookEater.dao.BookCategoryRepository;
import objectArmy.bookEater.entity.book.BookCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookCategoryService {
    private final BookCategoryRepository bookCategoryRepository;

    @Autowired
    public BookCategoryService(BookCategoryRepository bookCategoryRepository) {
        this.bookCategoryRepository = bookCategoryRepository;
    }

    public List<BookCategory> getBookCategories() {
        return this.bookCategoryRepository.findAll();
    }

    public BookCategory addBookCategory(BookCategory category) {
        return bookCategoryRepository.save(category);
    }

    public BookCategory findBookCategoryByName(String categoryName) {
        return bookCategoryRepository.findByName(categoryName);
    }

    public Optional<BookCategory> getBookCategoryByName(String categoryName) {
        return Optional.ofNullable(bookCategoryRepository.findByName(categoryName));
    }

    public BookCategory getBookCategoryOrElseCreate(String categoryName) {
        return getBookCategoryByName(categoryName).orElseGet(() -> {
            BookCategory category = new BookCategory();
            category.setName(categoryName);
            return addBookCategory(category);
        });
    }
}

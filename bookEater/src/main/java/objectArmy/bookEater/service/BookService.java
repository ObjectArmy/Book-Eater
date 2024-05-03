package objectArmy.bookEater.service;

import objectArmy.bookEater.dao.BookRepository;
import objectArmy.bookEater.entity.book.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Philip Athanasopoulos
 */
@Service
@Transactional
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final BookCategoryService bookCategoryService;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorService authorService, BookCategoryService bookCategoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.bookCategoryService = bookCategoryService;
    }

    public List<Book> getBooks() {
        return this.bookRepository.findAll();
    }

    public void saveBook(Book book) {
        book.getAuthors().forEach(this.authorService::saveAuthor);
        book.getCategories().forEach(this.bookCategoryService::saveCategory);
        this.bookRepository.save(book);
    }

}

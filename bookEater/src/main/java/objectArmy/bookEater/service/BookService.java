package objectArmy.bookEater.service;

import objectArmy.bookEater.dao.BookRepository;
import objectArmy.bookEater.entity.book.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Philip Athanasopoulos
 */
@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getBooks(){
        return this.bookRepository.findAll();
    }

    public void saveBook(Book book){
        this.bookRepository.save(book);
    }

}

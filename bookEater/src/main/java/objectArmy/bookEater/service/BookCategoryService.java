package objectArmy.bookEater.service;

import objectArmy.bookEater.dao.BookCategoryRepository;
import objectArmy.bookEater.entity.book.BookCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookCategoryService {
    @Autowired
    private BookCategoryRepository bookCategoryRepository;

    public List<BookCategory> getBookCategories(){
        return this.bookCategoryRepository.findAll();
    }

}

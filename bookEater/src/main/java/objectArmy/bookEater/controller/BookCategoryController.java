package objectArmy.bookEater.controller;

import objectArmy.bookEater.entity.book.BookCategory;
import objectArmy.bookEater.service.BookCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookCategoryController {
    @Autowired
    private BookCategoryService bookCategoryService;

    public List<BookCategory> getBookCategories(){
        return this.bookCategoryService.getBookCategories();
    }

}

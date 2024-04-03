package objectArmy.bookEater.controller;

import objectArmy.bookEater.dao.AuthorRepository;
import objectArmy.bookEater.dao.BookRepository;
import objectArmy.bookEater.entity.book.Author;
import objectArmy.bookEater.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping("/authors")
    public List<Author> getAuthors(){
        return this.authorService.getAuthors();
    }

}

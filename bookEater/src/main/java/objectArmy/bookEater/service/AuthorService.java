package objectArmy.bookEater.service;

import objectArmy.bookEater.dao.AuthorRepository;
import objectArmy.bookEater.entity.book.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAuthors() {
        return this.authorRepository.findAll();
    }

}

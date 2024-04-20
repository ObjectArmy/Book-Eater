package objectArmy.bookEater.service;

import objectArmy.bookEater.dao.AuthorRepository;
import objectArmy.bookEater.entity.book.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAuthors() {
        return this.authorRepository.findAll();
    }

    public Optional<Author> getAuthorByName(String authorName) {
        return Optional.ofNullable(authorRepository.findByName(authorName));
    }

    public Author getAuthorById(Long id) {
        return authorRepository.getReferenceById(id);
    }

    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Author getAuthorOrElseCreate(String authorName) {
        return getAuthorByName(authorName).orElseGet(() -> {
            Author author = new Author();
            author.setName(authorName);
            return addAuthor(author);
        });
    }
}




package kz.iitu.demo.service.impl;

import kz.iitu.demo.entity.Author;
import kz.iitu.demo.entity.Book;
import kz.iitu.demo.repository.AuthorRepository;
import kz.iitu.demo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author findAuthorById(Long id) {
        return authorRepository.findById(id).get();
    }

    @Override
    public Author findAuthorByName(String name) {
        return authorRepository.findByName(name);
    }


    @Override
    public void addAuthor(Author author) {
        authorRepository.saveAndFlush(author);
    }

    @Override
    public void deleteAuthor(Long id) {
        Optional<Author> authorOptional = authorRepository.findById(id);

        if (authorOptional.isPresent()) {
            authorRepository.deleteById(id);
        }
    }

    @Override
    public void updateAuthor(Author author) {
        Optional<Author> authorOptional = authorRepository.findById(author.getId());

        if (authorOptional.isPresent()) {
            Author dbAuthor = authorOptional.get();
            dbAuthor.setName(author.getName());
            dbAuthor.setBooks(author.getBooks());
            authorRepository.saveAndFlush(dbAuthor);
        }
    }
}

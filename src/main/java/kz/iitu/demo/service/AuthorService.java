package kz.iitu.demo.service;

import kz.iitu.demo.entity.Author;
import kz.iitu.demo.entity.User;

import java.util.List;

public interface AuthorService {
    public List<Author> findAllAuthors();

    public Author findAuthorById(Long id);

    public Author findAuthorByName(String name);

    public void addAuthor(Author author);

    public void deleteAuthor(Long id);
}

package kz.iitu.demo.controllers;


import kz.iitu.demo.entity.Author;
import kz.iitu.demo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;


@Controller
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    public List<Author> findAllAuthors() {
        return authorService.findAllAuthors();
    }

    public Author findAuthorById(Long id) {
        return authorService.findAuthorById(id);
    }
    public Author findAuthorByName(String name) {
        return authorService.findAuthorByName(name);
    }

    public void addAuthor(Author author) {
        authorService.addAuthor(author);
    }


    public void deleteAuthor(Long id) {
        authorService.deleteAuthor(id);
    }


}

package kz.iitu.demo.controllers;


import kz.iitu.demo.entity.Author;
import kz.iitu.demo.entity.Book;
import kz.iitu.demo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("")
    public List<Author> findAllAuthors() {
        return authorService.findAllAuthors();
    }

    @GetMapping("/{id}")
    public Author findAuthorById(@PathVariable Long id) {
        return authorService.findAuthorById(id);
    }


    @GetMapping("/find/")
    public Author findAuthorByName(@RequestParam String name) {
        return authorService.findAuthorByName(name);
    }

    @PostMapping("")
    public void addAuthor(@RequestBody Author author) {
        authorService.addAuthor(author);
    }


    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
    }

    @PutMapping("/{id}")
    public void updateAuthor(@PathVariable Long id,@RequestBody Author author) {
        author.setId(id);
        authorService.updateAuthor(author);
    }

}

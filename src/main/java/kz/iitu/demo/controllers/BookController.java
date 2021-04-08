package kz.iitu.demo.controllers;


import kz.iitu.demo.entity.Book;
import kz.iitu.demo.entity.BookStatus;
import kz.iitu.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("")
    public List<Book> getAll() {
        return bookService.findAllBooks();
    }

    @GetMapping("/find/")
    public List<Book> findBookByStatus(@RequestParam BookStatus bookStatus) {
        return bookService.findByStatus(bookStatus);
    }

    @GetMapping("/{id}")
    public Book findBookById(@PathVariable Long id) {
        return bookService.findBookById(id);
    }

    @GetMapping("/search/")
    public List<Book> searchBooks(@RequestParam String keyword) {
        return bookService.searchBooks(keyword);
    }

    @PostMapping("")
    public void addBook(@RequestBody Book book) {
        bookService.addBook(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @PutMapping("/{id}")
    public void updateBook(@PathVariable Long id,@RequestBody Book book) {
        book.setId(id);
        bookService.updateBook(book);
    }


}

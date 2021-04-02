package kz.iitu.demo.controllers;


import kz.iitu.demo.entity.Book;
import kz.iitu.demo.entity.BookStatus;
import kz.iitu.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;


@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    public List<Book> getAll() {
        return bookService.findAllBooks();
    }

    public List<Book> findBookByStatus(BookStatus bookStatus) {
        return bookService.findByStatus(bookStatus);
    }

    public Book findBookById(Long id) {
        return bookService.findBookById(id);
    }

    public List<Book> searchBooks(String keyword) {
        return bookService.searchBooks(keyword);
    }

    public void addBook(Book book) {
        bookService.addBook(book);
    }

    public void deleteBook(Long id) {
        bookService.deleteBook(id);
    }
    public void updateBook(Book book) {
        bookService.updateBook(book);
    }


}

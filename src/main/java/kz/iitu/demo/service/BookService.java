package kz.iitu.demo.service;

import kz.iitu.demo.entity.Book;
import kz.iitu.demo.entity.BookStatus;

import java.util.List;
import java.util.Optional;

public interface BookService{
    public List<Book> findAllBooks();

    public List<Book> searchBooks(String keyword);

    public List<Book> findByStatus(BookStatus bookStatus);

    public Book findBookById(Long id);

    public void addBook(Book book);

    public void deleteBook(Long id);

    public void updateBook(Book book);
}

package kz.iitu.demo.service.impl;

import kz.iitu.demo.entity.Book;
import kz.iitu.demo.entity.BookStatus;
import kz.iitu.demo.repository.BookRepository;
import kz.iitu.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book findBookById(Long id) {
        return bookRepository.findById(id).get();
    }


    @Override
    public List<Book> searchBooks(String keyword) {
        if (keyword != null) {
            return bookRepository.search(keyword);
        }
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findByStatus(BookStatus bookStatus) {
        return bookRepository.findByStatus(bookStatus);
    }

    @Override
    public void addBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);

        if (bookOptional.isPresent()) {
            bookRepository.deleteById(id);
        }
    }


    @Override
    public void updateBook(Book book) {
        Optional<Book> bookOptional = bookRepository.findById(book.getId());

        if (bookOptional.isPresent()) {
            Book dbBook = bookOptional.get();
            dbBook.setTitle(book.getTitle());
            dbBook.setDescription(book.getDescription());
            dbBook.setAuthor(book.getAuthor());
            dbBook.setStatus(book.getStatus());
            dbBook.setUser(book.getUser());
            bookRepository.save(dbBook);
        }
    }
}

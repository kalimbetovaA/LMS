package kz.iitu.demo;

import kz.iitu.demo.controllers.AuthorController;
import kz.iitu.demo.controllers.BookController;
import kz.iitu.demo.controllers.UserController;
import kz.iitu.demo.entity.Author;
import kz.iitu.demo.entity.Book;
import kz.iitu.demo.entity.BookStatus;
import kz.iitu.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class LMS {

    Scanner sc=new Scanner(System.in);

    @Autowired
    AuthorController authorController;
    @Autowired
    UserController userController;
    @Autowired
    BookController bookController;

    public void showMenu(){

        while (true) {
            System.out.println("What do you want to do: ");
            System.out.println("1 - Request Book\n" +
                    "2 - Issue Book\n" +
                    "3 - Return Book\n" +
                    "4 - Add Book\n" +
                    "5 - Search for Books by title, description, author\n" +
                    "6 - Show Requested, Issued, Returned Books\n" +
                    "7 - Show available books\n" +
                    "0 - exit");
            int choice = sc.nextInt();
            switch (choice){
                case 1:
                    updateBook(BookStatus.REQUESTED);
                    break;
                case 2:
                    updateBook(BookStatus.ISSUED);
                    break;
                case 3:
                    updateBook(BookStatus.RETURNED);
                    break;
                case 4:
                    addBook();
                    break;
                case 5:
                    searchBook();
                    break;
                case 6:
                    showBooks(BookStatus.REQUESTED);
                    System.out.println();
                    showBooks(BookStatus.ISSUED);
                    System.out.println();
                    showBooks(BookStatus.RETURNED);
                    System.out.println();
                    break;
                case 7:
                    showAvailability();
                    break;
                case 0:
                    return;
            }
        }
    }


    public void showBooks(){
        List<Book> books=bookController.getAll();
        for (Book b:books) {
            System.out.println(b.toString());
        }
    }

    public void showUsers(){
        List<User> users=userController.findAllUsers();
        for (User u:users) {
            System.out.println(u.toString());
        }
    }

    public void showAuthors(){
        List<Author> authors=authorController.findAllAuthors();
        for (Author a:authors) {
            System.out.println(a.toString());
        }
    }

    public void updateBook(BookStatus bookStatus){
        System.out.println("Choose user: ");
        showUsers();
        User user=userController.findUserById(sc.nextLong());
        System.out.println("Choose book: ");
        showBooks();
        Book book=bookController.findBookById(sc.nextLong());
        book.setStatus(bookStatus);
        book.setUser(user);
        bookController.updateBook(book);
    }

    public void addBook(){
        Book book = new Book();
        System.out.println("Enter title: ");
        book.setTitle(sc.next());
        System.out.println("Enter description: ");
        book.setDescription(sc.next());
        System.out.println("Enter author name: ");
        String authorName=sc.next();
        Author author = authorController.findAuthorByName(authorName);
        author.setName(authorName);
        book.setAuthor(author);
        System.out.println("Choose user: ");
        showUsers();
        User user=userController.findUserById(sc.nextLong());
        book.setUser(user);
        book.setStatus(BookStatus.AVAILABLE);

        bookController.addBook(book);
    }

    public void searchBook(){
        System.out.println("Enter keyword: ");
        List<Book> books = bookController.searchBooks(sc.next());
        for (Book b:books) {
            System.out.println(b.toString());
        }
    }

    public void showAvailability(){
        System.out.println("Available Books:");
        List<Book> books = bookController.findBookByStatus(BookStatus.AVAILABLE);
        books.addAll(bookController.findBookByStatus(BookStatus.RETURNED));
        for (Book b:books) {
            System.out.println(b.getTitle()+"\nDescription: "+b.getDescription()+"\nAuthor: "+b.getAuthor().getName());
            System.out.println();
        }
    }


    public void showBooks(BookStatus bookStatus){
        System.out.println(bookStatus+":");
        List<Book> books=bookController.findBookByStatus(bookStatus);
        for (Book b:books) {
            System.out.println(b.toString());
        }
    }


}

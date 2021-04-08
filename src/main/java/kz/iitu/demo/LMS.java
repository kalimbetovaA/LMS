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

import java.util.ArrayList;
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
            System.out.println("1 - Add Book\n" +
                    "2 - Request Book\n" +
                    "3 - Issue Book\n" +
                    "4 - Return Book\n" +
                    "5 - Search for Books by title, description, author\n" +
                    "6 - Show Requested, Issued, Returned Books\n" +
                    "7 - Show available books\n" +
                    "0 - exit");
            int choice = sc.nextInt();
            switch (choice){
                case 1:
                    addBook();
                    break;
                case 2:
                    updateBook(BookStatus.REQUESTED);
                    break;
                case 3:
                    updateBook(BookStatus.ISSUED);
                    break;
                case 4:
                    updateBook(BookStatus.RETURNED);
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

    public void showAuthors(List<Author> authors){
        for (Author a:authors) {
            System.out.println(a.toString());
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
        if(bookStatus==BookStatus.RETURNED){
            showUserBooks(user, BookStatus.ISSUED);
        }else if(bookStatus==BookStatus.ISSUED){
            showUserBooks(user, BookStatus.REQUESTED);
        }
        else if(bookStatus==BookStatus.REQUESTED){
            showBooks(BookStatus.AVAILABLE);
            showBooks(BookStatus.RETURNED);
        }
        Book book=bookController.findBookById(sc.nextLong());
        book.setStatus(bookStatus);
        book.setUser(user);
        bookController.updateBook(book.getId(), book);
    }

    public void addBook(){
        Book book = new Book();
        System.out.println("Enter title: ");
        book.setTitle(sc.next());
        System.out.println("Enter description: ");
        book.setDescription(sc.next());
        List<Author> authors = new ArrayList<>();
        while (true) {
            System.out.println("Choose author of the book: ");
            showAuthors(authorController.findAllAuthors());
            System.out.println("0 - Done");
            Long authorId=sc.nextLong();
            if(authorId==0) {
                break;
            }
            Author author = authorController.findAuthorById(authorId);
            authors.add(author);

        }
        book.setAuthors(authors);
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
            System.out.println(b.getTitle()+
                    "\nDescription: "+b.getDescription()+"" +
                    "\nAuthors: ");
                    showAuthors(b.getAuthors());
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

    public void showUserBooks(User user, BookStatus bookStatus){
        List<Book> books=bookController.findBookByStatus(bookStatus);
        for (Book b:books) {
            if(b.getUser().getUsername().equals(user.getUsername())){
                System.out.println(b.toString());
            }
        }
    }


}

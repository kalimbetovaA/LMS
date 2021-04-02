package kz.iitu.demo.service;

import kz.iitu.demo.entity.Book;
import kz.iitu.demo.entity.User;

import java.util.List;


public interface UserService {
    public List<User> findAllUsers();

    public User findUserById(Long id);


    public void addUser(User user);

    public void deleteUser(Long id);
}

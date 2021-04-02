package kz.iitu.demo.controllers;

import kz.iitu.demo.entity.User;
import kz.iitu.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    public User findUserById(Long id) {
        return userService.findUserById(id);
    }

    public void addUser(User user) {
        userService.addUser(user);
    }

    public void deleteUser(Long id) {
        userService.deleteUser(id);
    }


}

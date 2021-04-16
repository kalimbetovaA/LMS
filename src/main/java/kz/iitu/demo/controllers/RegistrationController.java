package kz.iitu.demo.controllers;

import kz.iitu.demo.entity.User;
import kz.iitu.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public void register(@RequestBody User user) {
        userService.addUser(user);
    }
}

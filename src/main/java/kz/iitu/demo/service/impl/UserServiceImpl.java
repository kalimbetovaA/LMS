package kz.iitu.demo.service.impl;

import kz.iitu.demo.entity.Author;
import kz.iitu.demo.entity.User;
import kz.iitu.demo.repository.UserRepository;
import kz.iitu.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).get();
    }


    @Override
    public void addUser(User user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            userRepository.deleteById(id);
        }
    }

    @Override
    public void updateUser(User user) {
        Optional<User> userOptional = userRepository.findById(user.getId());

        if (userOptional.isPresent()) {
            User dbUser = userOptional.get();
            dbUser.setUsername(user.getUsername());
            dbUser.setBooks(user.getBooks());
            userRepository.saveAndFlush(dbUser);
        }
    }
}

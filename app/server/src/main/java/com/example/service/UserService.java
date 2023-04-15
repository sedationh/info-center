package com.example.service;

import com.example.domin.entity.User;
import com.example.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Boolean isUserSet() {
        return !userRepository.findAll().isEmpty();
    }

    public void createUser(String username, String password) throws Exception {
        List<User> all = userRepository.findAll();
        if (!all.isEmpty()) {
            throw new Exception("User already exists");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userRepository.save(user);
    }

    public User getUser(String username) {
        User user = userRepository.findByUsername(username);
        return user;
    }
}

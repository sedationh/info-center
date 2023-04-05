package com.info_center.server.service;

import com.info_center.server.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Boolean isUserSet() {
        return !userRepository.findAll().isEmpty();
    }
}

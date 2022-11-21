package com.example.cards.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email).get(0);
    }

    public boolean validateToken(String token) {
        if (token != null && !token.trim().isEmpty()) {
            return !userRepository.getUserByEmailAndPassword(token.split(":")[0], token.split(":")[1]).isEmpty();
        }
        return false;
    }
}

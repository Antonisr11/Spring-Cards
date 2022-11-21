package com.example.cards.Login;


import com.example.cards.Users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UserRepository userRepository;

    @Autowired
    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public String token(String email, String password) {
        if (!userRepository.getUserByEmailAndPassword(email, password).isEmpty()) {
            return email + ":" + password;
        }
        return "";
    }
}

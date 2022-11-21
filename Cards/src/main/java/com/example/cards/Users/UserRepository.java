package com.example.cards.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM users WHERE email=?1 and password=?2", nativeQuery = true)
    List<User> getUserByEmailAndPassword(String email, String password);

    @Query(value = "SELECT * FROM users WHERE email=?1 LIMIT 1", nativeQuery = true)
    List<User> getUserByEmail(String email);


}

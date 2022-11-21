package com.example.cards.Cards;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {

    @Query(value = "SELECT * FROM cards WHERE owner_email=?1", nativeQuery = true)
    List<Card> findCardsByOwnerEmail(String owner_email);

    @Query(value = "SELECT * FROM cards WHERE status=?1 and owner_email=?2", nativeQuery = true)
    List<Card> findCardsByStatus(int status, String email);

    @Query(value = "SELECT * FROM cards WHERE name like CONCAT('%',?1,'%') and owner_email=?2", nativeQuery = true)
    List<Card> findCardsByName(String name, String email);

    @Query(value = "SELECT * FROM cards WHERE color=?1 and owner_email=?2", nativeQuery = true)
    List<Card> findCardsByColor(String color, String email);

    @Query(value = "SELECT * FROM cards WHERE creation_date=?1 and owner_email=?2", nativeQuery = true)
    List<Card> findCardsByDate(String creation_date, String email);
}

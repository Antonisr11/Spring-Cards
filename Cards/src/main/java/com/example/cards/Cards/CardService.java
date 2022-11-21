package com.example.cards.Cards;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CardService {
    private final CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public CardRepository getCardRepository() {
        return cardRepository;
    }

    public List<Card> getCardsByEmail(String email) {
        return cardRepository.findCardsByOwnerEmail(email);
    }

    public Card getCardByID(int ID) {
        return cardRepository.findAllById(Collections.singleton(ID)).get(0);
    }

    public List<Card> getCardsByStatusFilter(String status, String email) {
        if (status.equals("inprogress")) {
            return cardRepository.findCardsByStatus(1, email);
        } else if (status.equals("done")) {
            return cardRepository.findCardsByStatus(2, email);
        } else {
            return cardRepository.findCardsByStatus(0, email);
        }
    }

    public List<Card> getCardsByNameFilter(String name, String email) {
        return cardRepository.findCardsByName(name, email);
    }

    public List<Card> getCardsByColorFilter(String color, String email) {
        return cardRepository.findCardsByColor(color, email);
    }

    public List<Card> getCardsByDateFilter(String date, String email) {
        return cardRepository.findCardsByDate(date, email);
    }

    public void insertOrUpdateCard(Card card) {
        cardRepository.save(card);
    }

    public void deleteCardByID(int ID) {
        cardRepository.deleteById(ID);
    }

}

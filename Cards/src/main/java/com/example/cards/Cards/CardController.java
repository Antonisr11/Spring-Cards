package com.example.cards.Cards;

import com.example.cards.CardsApplication;
import com.example.cards.Users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.example.cards.CardsApplication.redirectToShowCards;

@Controller
@RequestMapping(value = "card", method = RequestMethod.GET)
public class CardController {
    private final CardService cardService;
    private final UserService userService;

    public CardService getCardService() {
        return cardService;
    }

    public UserService getUserService() {
        return userService;
    }

    @Autowired
    public CardController(CardService cardService, UserService userService) {
        this.cardService = cardService;
        this.userService = userService;
    }

    @RequestMapping(value = {"showCards/", "showCards"}, method = RequestMethod.GET)
    public String showCards(Model model) {
        if (!userService.validateToken(CardsApplication.getToken())) {
            return "logout.html";
        }
        String email = CardsApplication.getToken().split(":")[0];
        model.addAttribute("cardsList", cardService.getCardsByEmail(email));
        return "showCards.html";
    }

    @GetMapping(value = "filter/status/{status_name}")
    public String filter_status(@PathVariable String status_name, Model model) {
        if (!userService.validateToken(CardsApplication.getToken())) {
            return "logout.html";
        }
        String email = CardsApplication.getToken().split(":")[0];
        model.addAttribute("cardsList", cardService.getCardsByStatusFilter(status_name, email));
        return "showCards.html";
    }

    @GetMapping(value = "filter/name/{name}")
    public String filter_name(@PathVariable String name, Model model) {
        if (!userService.validateToken(CardsApplication.getToken())) {
            return "logout.html";
        }
        String email = CardsApplication.getToken().split(":")[0];
        model.addAttribute("cardsList", cardService.getCardsByNameFilter(name, email));
        return "showCards.html";
    }

    @GetMapping(value = "filter/color/{color_hex}")
    public String filter_color(@PathVariable String color_hex, Model model) {
        if (!userService.validateToken(CardsApplication.getToken())) {
            return "logout.html";
        }
        String email = CardsApplication.getToken().split(":")[0];
        model.addAttribute("cardsList", cardService.getCardsByColorFilter('#' + color_hex, email));
        return "showCards.html";
    }

    @GetMapping(value = "filter/date/{date}")
    public String filter_date(@PathVariable String date, Model model) {
        if (!userService.validateToken(CardsApplication.getToken())) {
            return "logout.html";
        }
        String email = CardsApplication.getToken().split(":")[0];
        model.addAttribute("cardsList", cardService.getCardsByDateFilter(date, email));
        return "showCards.html";
    }

    @GetMapping(value = "show/{ID}")
    public String show(@PathVariable String ID, Model model) {
        if (!userService.validateToken(CardsApplication.getToken())) {
            return "logout.html";
        }
        model.addAttribute("card", cardService.getCardByID(Integer.parseInt(ID)));
        return "showCard.html";
    }

    @PostMapping(value = "insert/")
    public String insert(Model model) {
        if (!userService.validateToken(CardsApplication.getToken())) {
            return "logout.html";
        }
        model.addAttribute("insert", "True");
        model.addAttribute("card", new Card());
        return "insertCard.html";
    }

    @PostMapping(value = "update/{ID}")
    public String update(@PathVariable String ID, Model model) {
        if (!userService.validateToken(CardsApplication.getToken())) {
            return "logout.html";
        }
        model.addAttribute("card", cardService.getCardByID(Integer.parseInt(ID)));
        return "updateCard.html";
    }

    @PostMapping(value = "commitInsert/")
    @ResponseBody
    public String commitInsert(@RequestParam("color") String color, @RequestParam("description") String description, @RequestParam("name") String name) {
        if (!userService.validateToken(CardsApplication.getToken())) {
            return "logout.html";
        }
        String email = CardsApplication.getToken().split(":")[0];
        cardService.insertOrUpdateCard(new Card(color, description, name, userService.getUserByEmail(email)));
        return redirectToShowCards();
    }

    @PostMapping(value = "commitUpdate/")
    @ResponseBody
    public String commitUpdate(@RequestParam("ID") String ID, @RequestParam("color") String color, @RequestParam("description") String description, @RequestParam("name") String name, @RequestParam("status") String status) {
        if (!userService.validateToken(CardsApplication.getToken())) {
            return "logout.html";
        }
        Card tempCard = cardService.getCardByID(Integer.parseInt(ID));
        tempCard.setColor(color);
        tempCard.setDescription(description);
        tempCard.setName(name);
        tempCard.setStatus(Integer.parseInt(status));
        cardService.insertOrUpdateCard(tempCard);
        return redirectToShowCards();
    }

    @PostMapping(value = "commitDelete/")
    @ResponseBody
    public String delete(@RequestParam("ID") String ID) {
        if (!userService.validateToken(CardsApplication.getToken())) {
            return "logout.html";
        }
        cardService.deleteCardByID(Integer.parseInt(ID));
        return redirectToShowCards();
    }

}

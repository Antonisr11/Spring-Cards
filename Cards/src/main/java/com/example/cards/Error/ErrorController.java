package com.example.cards.Error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.example.cards.CardsApplication.redirectToShowCards;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @RequestMapping("/error")
    @ResponseBody
    public String handleError() {
        return redirectToShowCards();
    }

    public String getErrorPath() {
        return "/error";
    }

}

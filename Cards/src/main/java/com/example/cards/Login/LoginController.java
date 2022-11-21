package com.example.cards.Login;

import com.example.cards.CardsApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    public LoginService getLoginService() {
        return loginService;
    }

    @GetMapping(value = {"/", ""})
    public String main() {
        CardsApplication.setToken(null);
        return "login.html";
    }

    @PostMapping(value = {"token/", "token"})
    @ResponseBody
    public String token(@RequestParam("mail") String email, @RequestParam("password") String password) {
        CardsApplication.setToken(loginService.token(email, password));
        return "<html>   <head>   <script> window.onload = function() { window.location.replace(\"http://localhost:8080/card/showCards\"); } </script>     <title></title>   </head>  <body></body> </html> ";
    }

}

package com.example.cards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;
import java.net.URL;

@SpringBootApplication
public class CardsApplication {

    private static String token;

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        CardsApplication.token = token;
    }

    public static void main(String[] args) {
        SpringApplication.run(CardsApplication.class, args);
        startLocalhostInBrowser();
    }

    public static void startLocalhostInBrowser() {
        System.setProperty("java.awt.headless", "false");
        try {
            Desktop.getDesktop().browse(new URL("http://localhost:8080").toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String redirectToShowCards() {
        return "<html>   <head>   <script> window.onload = function() { window.location.replace(\"http://localhost:8080/card/showCards\"); } </script>     <title></title>   </head>  <body></body> </html> ";
    }

}

package it.unipi.BGnet.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GamePageController {
    @GetMapping("/gamePage")
    public String gamePage(){
        return "dynamicGamePage";
    }
}
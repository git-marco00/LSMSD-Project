package it.unipi.BGnet.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GamePageController {
    @RequestMapping("/gamePage")
    public String gamePage(){
        return "dynamicGamePage";
    }
}

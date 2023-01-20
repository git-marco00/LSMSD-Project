package it.unipi.BGnet.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProfileController {
    @RequestMapping("/userProfile")
    public String profilePage(){
        return "userProfile";
    }
}

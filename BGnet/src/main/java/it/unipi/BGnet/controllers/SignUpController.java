package it.unipi.BGnet.controllers;

import jakarta.annotation.security.PermitAll;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignUpController {
    @GetMapping("/signup")
    public String signupPage(){
        return "signup";
    }
}

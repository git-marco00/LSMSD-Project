package it.unipi.BGnet.controllers;

import it.unipi.BGnet.Utilities.SessionVariables;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("sessionVariables")
public class HomeController {
    @RequestMapping("/")
    public String home(Model model) {
        if((SessionVariables) model.getAttribute("sessionVariables") == null)
            model.addAttribute("sessionVariables", new SessionVariables());
        return "home";
    }
}

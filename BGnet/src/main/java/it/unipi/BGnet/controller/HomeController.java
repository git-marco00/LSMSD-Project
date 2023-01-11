package it.unipi.BGnet.controller;

import it.unipi.BGnet.Utilities.SessionVariables;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("sessionVariables")
public class HomeController
{
    @GetMapping("/")
    public String home(Model model)
    {
        model.addAttribute("sessionVariables", new SessionVariables());
        return "home";
    }
}

package it.unipi.BGnet.controllers;

import it.unipi.BGnet.Utilities.SessionVariables;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
    @RequestMapping("/adminPage")
    public String admin(Model model)
    {
        if((SessionVariables) model.getAttribute("sessionVariables") == null)
            model.addAttribute("sessionVariables", new SessionVariables());
        return "adminPage";
    }
}

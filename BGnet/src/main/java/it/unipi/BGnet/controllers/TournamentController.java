package it.unipi.BGnet.controllers;

import it.unipi.BGnet.Utilities.SessionVariables;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("sessionVariables")
public class TournamentController {
    @RequestMapping("/tournamentPage")
    public String tournamentPage(Model model){
        if(((SessionVariables) model.getAttribute("sessionVariables")).myself == null)
            return "login";
        return "tournamentPage";}
}

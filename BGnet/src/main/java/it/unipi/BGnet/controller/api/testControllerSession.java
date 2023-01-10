package it.unipi.BGnet.controller.api;

import it.unipi.BGnet.Utilities.Constants;
import it.unipi.BGnet.Utilities.SessionVariables;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.List;

@RestController
@SessionAttributes("sessionAttributes")
public class testControllerSession {
    @RequestMapping("/api/setButton1")
    public static void changeButton1(Model model){
        SessionVariables sv = new SessionVariables();
        sv.myself = "marco";
        sv.gameToDisplay = "Libertalia";
        sv.userToDisplay = "boh";
        model.addAttribute("sessionAttributes", sv);
    }

    @RequestMapping("/api/setButton2")
    public static void changeButton2(Model model){
        SessionVariables sv = new SessionVariables();
        sv.myself = "Angelo";
        sv.gameToDisplay = "Cane";
        sv.userToDisplay = "fede";
        model.addAttribute("sessionAttributes", sv);
    }
}

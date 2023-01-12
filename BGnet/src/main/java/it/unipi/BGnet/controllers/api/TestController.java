package it.unipi.BGnet.controllers.api;


import it.unipi.BGnet.Utilities.Constants;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

@RestController
//@SessionAttributes({Constants.CURRENT_USER, Constants.CURRENT_GAME})
@SessionAttributes("sessionAttributes")
public class TestController {

    //@RequestMapping("/api/setButton1")
    public static void changeButton1(Model model){
        model.addAttribute(Constants.CURRENT_USER, "Cane");
        model.addAttribute(Constants.CURRENT_GAME, "CIAOOOOOO");
    }

    //@RequestMapping("/api/setButton2")
    public static void changeButton2(Model model){
        model.addAttribute(Constants.CURRENT_GAME, "Gatto");
    }

    @RequestMapping("/api/getButton")
    public static Object returnLabel(Model model){
        return model.getAttribute("sessionAttributes");
    }
}

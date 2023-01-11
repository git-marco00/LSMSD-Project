package it.unipi.BGnet.controller.api;
import com.google.gson.GsonBuilder;
import it.unipi.BGnet.DTO.GamePage;
import it.unipi.BGnet.Utilities.Constants;
import it.unipi.BGnet.Utilities.SessionVariables;
import it.unipi.BGnet.service.pages.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.gson.Gson;

@RestController
@SessionAttributes("sessionVariables")
public class GameController {
    @Autowired
    GameService gameService;
    @RequestMapping("/api/loadGamePage")
    @ResponseBody
    public String loadGamePage(Model model){
        ////////////////////////// ATTENZIONE DA DEBUGGARE //////////////////////////
        SessionVariables sv = (SessionVariables) model.getAttribute("sessionVariables");
        sv.myself = "marco";
        GamePage page = gameService.getGamePage(sv.myself, sv.gameToDisplay);
        page.setRatings(0);
        page.makeRandomPosts();
        Gson gson = new GsonBuilder().serializeSpecialFloatingPointValues().create();
        return gson.toJson(page);
    }

    @RequestMapping("/api/gamePageExists")
    public boolean gamePageExists(Model model, @RequestParam("name") String name){
        if(gameService.getExistence(name)){
            SessionVariables sv = (SessionVariables) model.getAttribute("sessionVariables");
            sv.gameToDisplay = name;
            model.addAttribute("sessionVariables", sv);
            return true;
        }
        return false;
    }


}

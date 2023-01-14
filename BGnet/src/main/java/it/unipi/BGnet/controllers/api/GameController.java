package it.unipi.BGnet.controllers.api;
import com.google.gson.GsonBuilder;
import it.unipi.BGnet.DTO.GamePage;
import it.unipi.BGnet.Utilities.SessionVariables;
import it.unipi.BGnet.service.pages.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.google.gson.Gson;

@RestController
@SessionAttributes("sessionVariables")
public class GameController {
    @Autowired
    GameService gameService;
    Logger logger = LoggerFactory.getLogger(GameController.class);
    @RequestMapping("/api/loadGamePage")
    @ResponseBody
    public String loadGamePage(Model model){
        ////////////////////////// ATTENZIONE DA DEBUGGARE //////////////////////////
        SessionVariables sv = (SessionVariables) model.getAttribute("sessionVariables");
        GamePage page = gameService.getGamePage(sv.myself, sv.gameToDisplay);
        page.setRatings(0);
        Gson gson = new GsonBuilder().serializeSpecialFloatingPointValues().create();
        return gson.toJson(page);
    }
    @RequestMapping("/api/gamePageExists")
    public boolean gamePageExists(Model model, @RequestParam("name") String name){
        logger.warn(name);
        if(gameService.getExistence(name)){
            SessionVariables sv = (SessionVariables) model.getAttribute("sessionVariables");
            sv.gameToDisplay = name;
            model.addAttribute("sessionVariables", sv);
            return true;
        }
        return false;
    }
}

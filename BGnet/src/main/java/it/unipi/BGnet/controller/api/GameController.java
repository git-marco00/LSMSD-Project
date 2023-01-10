package it.unipi.BGnet.controller.api;
import it.unipi.BGnet.DTO.GamePage;
import it.unipi.BGnet.Utilities.Constants;
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
@SessionAttributes({Constants.CURRENT_USER, Constants.CURRENT_GAME})
public class GameController {
/*
    @RequestMapping("/api/game")
    public String returnList(){
        GameDTO game = new GameDTO();
        Gson gson= new Gson();
        String json = gson.toJson(game);
        System.out.println(json);
        return json;
    }

 */
    @Autowired
    GameService gameService;
    @RequestMapping("/api/loadGamePage")
    public String loadGamePage(Model model){
        ////////////////////////// ATTENZIONE DA DEBUGGARE //////////////////////////
        model.addAttribute(Constants.CURRENT_USER, "marco");
        /////////////////////////////////////////////////////////////////////////////
        GamePage page = gameService.getGamePage((String) model.getAttribute(Constants.CURRENT_USER), (String) model.getAttribute(Constants.CURRENT_GAME));
        return new Gson().toJson(page);
    }

    @RequestMapping("api/gamePageExists/")
    @ResponseBody
    public boolean gamePageExists(Model model, @RequestParam String name){
        if(gameService.getExistence(name)){
            model.addAttribute(Constants.CURRENT_GAME, name);
            return true;
        }

        return false;
    }
}
package it.unipi.BGnet.controllers.api;

import com.google.gson.GsonBuilder;

import it.unipi.BGnet.model.Game;
import it.unipi.BGnet.DTO.GameDTO;
import it.unipi.BGnet.DTO.GamePage;
import it.unipi.BGnet.service.pages.GameService;
import it.unipi.BGnet.Utilities.SessionVariables;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import java.util.List;

@RestController
@SessionAttributes("sessionVariables")
public class GameController {
    @Autowired
    GameService gameService;
    @GetMapping("/api/loadGamePage")
    public @ResponseBody String loadGamePage(Model model){
        ////////////////////////// ATTENZIONE DA DEBUGGARE //////////////////////////
        SessionVariables sv = (SessionVariables) model.getAttribute("sessionVariables");
        GamePage page = gameService.getGamePage(sv.myself, sv.gameToDisplay);
        Gson gson = new GsonBuilder().serializeSpecialFloatingPointValues().create();
        return gson.toJson(page);
    }
    @GetMapping("/api/gamePageExists")
    public @ResponseBody String gamePageExists(Model model, @RequestParam("name") String name){
        List<Game> list = gameService.checkExistence(name);
        int counts = list.size();
        if(counts > 0) {
            if(counts > 1) {
                SessionVariables sv = (SessionVariables) model.getAttribute("sessionVariables");
                sv.current_results = list;
                return new Gson().toJson(counts);
            }
            else {
                if(list.get(0).getName().equals(name)) {
                    SessionVariables sv = (SessionVariables) model.getAttribute("sessionVariables");
                    sv.gameToDisplay = name;
                    model.addAttribute("sessionVariables", sv);
                    return new Gson().toJson(1);
                }
                else {
                    SessionVariables sv = (SessionVariables) model.getAttribute("sessionVariables");
                    sv.current_results = list;
                    return new Gson().toJson(0);
                }
            }
        }
        return new Gson().toJson(-1);
    }
    @GetMapping("/api/loadResultsPage")
    public @ResponseBody String loadResultsPage(Model model) {
        ////////////////////////// ATTENZIONE DA DEBUGGARE //////////////////////////
        SessionVariables sv = (SessionVariables) model.getAttribute("sessionVariables");
        List<GameDTO> page = gameService.getResultPage(sv.current_results);
        Gson gson = new GsonBuilder().serializeSpecialFloatingPointValues().create();
        return gson.toJson(page);
    }
    @GetMapping("api/followGame")
    public @ResponseBody boolean followGame(Model model,@RequestParam("game") String game) {
        SessionVariables sv = (SessionVariables) model.getAttribute("sessionVariables");
        if(sv == null || sv.myself == null)
            return false;
        return gameService.followGame(sv.myself, game);
    }
    @GetMapping("api/unfollowGame")
    public @ResponseBody boolean unfollowGame(Model model,@RequestParam("game") String game) {
        SessionVariables sv = (SessionVariables) model.getAttribute("sessionVariables");
        if(sv == null || sv.myself == null)
            return false;
        return gameService.unfollowGame(sv.myself, game);
    }
}

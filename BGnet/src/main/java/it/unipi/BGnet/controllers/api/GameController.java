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
    @GetMapping("/api/searchGameFiltered")
    public @ResponseBody String gamePageExistsFiltered(Model model, @RequestParam("name") String name, @RequestParam("category") String category){
        List<Game> list = gameService.checkExistenceFiltered(name, category);
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
    public @ResponseBody String followGame(Model model, @RequestParam("game") String game) {
        Gson gson = new Gson();
        SessionVariables sv = (SessionVariables) model.getAttribute("sessionVariables");
        if(sv == null || sv.myself == null) {
            return gson.toJson(false);
        }
        return gson.toJson(gameService.followGame(sv.myself, game));
    }
    @GetMapping("api/unfollowGame")
    public @ResponseBody String unfollowGame(Model model, @RequestParam("game") String game) {
        Gson gson = new GsonBuilder().serializeSpecialFloatingPointValues().create();
        SessionVariables sv = (SessionVariables) model.getAttribute("sessionVariables");
        if(sv == null || sv.myself == null) {
            return gson.toJson(false);
        }
        return gson.toJson(gameService.unfollowGame(sv.myself, game));
    }
    @GetMapping("api/rateGame")
    public @ResponseBody boolean rateGame(Model model, @RequestParam("rate") int rate) {
        SessionVariables sv = (SessionVariables) model.getAttribute("sessionVariables");
        return gameService.rateGame(sv.myself, sv.gameToDisplay, rate);
    }
    @GetMapping("api/unrateGame")
    public @ResponseBody boolean unrateGame(Model model) {
        SessionVariables sv = (SessionVariables) model.getAttribute("sessionVariables");
        return gameService.unrateGame(sv.myself, sv.gameToDisplay);
    }
    @GetMapping("api/haveIVoted")
    public boolean haveIVoted(Model model){
        SessionVariables sv = (SessionVariables) model.getAttribute("sessionVariables");
        return gameService.haveIRated(sv.myself, sv.gameToDisplay);
    }
}

package it.unipi.BGnet.controllers.api;

import com.google.gson.Gson;
import it.unipi.BGnet.DTO.AnalyticDTO;
import it.unipi.BGnet.service.user.ManagerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Arrays;

@RestController
public class AdminCRUDController {
    @Autowired
    private ManagerService managerService;
    @GetMapping("/api/createGame")
    public boolean createGame(@RequestParam("name")String name, @RequestParam("designer")String designer, @RequestParam("year")int yearPublished,
                              @RequestParam("minPlayers")int minPlayers, @RequestParam("maxPlayers")int maxPlayers,
                              @RequestParam("playingTime")String playingTime, @RequestParam("minPlayTime")String minPlayTime,
                              @RequestParam("maxPlayTime")String maxPlayTime, @RequestParam("categories")String categories,
                              @RequestParam("description")String description, @RequestParam("img")String img)
    {
        List<String> cat = Arrays.asList(categories.split("\\s*,\\s*"));
        return managerService.addGame(name, designer, yearPublished, minPlayers, maxPlayers, playingTime, minPlayTime, maxPlayTime, cat, description, img);
    }
    @GetMapping("/api/deleteGame")
    public boolean deleteGame(@RequestParam("name") String name){
        return managerService.deleteGame(name);
    }
    @GetMapping("/api/deletePost")
    public boolean deletePost(@RequestParam("id") String id){
        return managerService.deletePost(id);
    }
    @GetMapping("/api/banUser")
    public boolean deleteUser(@RequestParam("name") String name){
        return managerService.deleteUser(name);
    }
    @GetMapping("/api/analytic1")
    public String analytic1(){
        List<AnalyticDTO> analyticDTO = managerService.analytic1();
        Gson gson = new Gson();
        return gson.toJson(analyticDTO);
    }
    @GetMapping("/api/analytic2")
    public String analytic2(@RequestParam("year") int year){
        List<AnalyticDTO> analyticDTO = managerService.analytic2(year);
        Gson gson = new Gson();
        return gson.toJson(analyticDTO);
    }
    @GetMapping("/api/analytic3")
    public String analytic3(@RequestParam("year") int year){
        List<AnalyticDTO> analyticDTO = managerService.analytic3(year);
        Gson gson = new Gson();
        return gson.toJson(analyticDTO);
    }
    @GetMapping("/api/analytic4")
    public String analytic4(){
        List<AnalyticDTO> analyticDTO = managerService.analytic4();
        Gson gson = new Gson();
        return gson.toJson(analyticDTO);
    }
    @GetMapping("/api/analytic5")
    public String analytic5(){
        List<AnalyticDTO> analyticDTO = managerService.analytic5();
        Gson gson = new Gson();
        return gson.toJson(analyticDTO);
    }
}

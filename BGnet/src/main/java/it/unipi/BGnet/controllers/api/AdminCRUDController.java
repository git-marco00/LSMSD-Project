package it.unipi.BGnet.controllers.api;


import it.unipi.BGnet.service.user.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminCRUDController {
    @Autowired
    private ManagerService managerService;

    @PutMapping("/api/createGame")
    public boolean createGame(@RequestParam("name")String name, @RequestParam("designer")String designer, @RequestParam("year")int yearPublished,
                              @RequestParam("minPlayers")int minPlayers, @RequestParam("maxPlayers")int maxPlayers,
                              @RequestParam("playingTime")String playingTime, @RequestParam("minPlayingTime")String minPlayTime,
                              @RequestParam("maxPlayingTime")String maxPlayTime, @RequestParam("categories")List<String> categories,
                              @RequestParam("description")String description, @RequestParam("img")String img)
    {
        return managerService.addGame(name, designer, yearPublished, minPlayers, maxPlayers, playingTime, minPlayTime, maxPlayTime, categories, description, img);
    }

    @PutMapping("/api/deleteGame")
    public boolean deleteGame(@RequestParam("name") String name){
        return managerService.deleteGame(name);
    }

    @PutMapping("/api/deletePost")
    public boolean deletePost(){return false;}

    @PutMapping("/api/deleteComment")
    public boolean deleteComment(){return false;}

    @PutMapping("/api/banUser")
    public boolean deleteUser(){return false;}
}

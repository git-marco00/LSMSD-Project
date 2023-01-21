package it.unipi.BGnet.controllers.api;


import it.unipi.BGnet.service.user.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
}

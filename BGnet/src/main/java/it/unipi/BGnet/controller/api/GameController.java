package it.unipi.BGnet.controller.api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

@RestController
public class GameController {

    @RequestMapping("/api/game")
    public String returnList(){
        GameDTO game = new GameDTO();
        Gson gson= new Gson();
        String json = gson.toJson(game);
        System.out.println(json);
        return json;
    }
}
package it.unipi.BGnet.controllers.api;

import com.google.gson.Gson;

import it.unipi.BGnet.DTO.GameDTO;
import it.unipi.BGnet.DTO.UserDTO;
import it.unipi.BGnet.Utilities.SessionVariables;
import it.unipi.BGnet.service.user.UserService;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@RestController
@SessionAttributes("sessionVariables")
public class LandingController {
    @Autowired
    UserService userService;
    @RequestMapping("/api/landingSuggestedGames")
    public @ResponseBody String returnListSuggestedGames(Model model) {
        List<GameDTO> list;
        Gson gson = new Gson();
        SessionVariables sv = (SessionVariables) model.getAttribute("sessionVariables");
        if(sv == null || sv.myself == null) {
            //list = userService.getMostFamousGames();
            list = userService.getRandomGames();
        } else {
            list = userService.getSuggestedGames(sv.myself);
        }
        return gson.toJson(list);
    }
    @RequestMapping("/api/landingSuggestedUsers")
    public @ResponseBody String returnListSuggestedUsers(Model model) {
        List<UserDTO> list;
        Gson gson = new Gson();
        SessionVariables sv = (SessionVariables) model.getAttribute("sessionVariables");
        if(sv == null || sv.myself == null) {
            //list = userService.getMostFamousUsers();
            list = userService.getRandomUsers();
        } else {
            list = userService.getSuggestedUsers(sv.myself);
        }
        return gson.toJson(list);
    }
}

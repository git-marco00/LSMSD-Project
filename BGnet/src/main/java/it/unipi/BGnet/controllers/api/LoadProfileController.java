package it.unipi.BGnet.controllers.api;

import com.google.gson.Gson;

import it.unipi.BGnet.DTO.UserDTO;
import it.unipi.BGnet.service.user.UserService;
import it.unipi.BGnet.Utilities.SessionVariables;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@SessionAttributes("sessionVariables")
public class LoadProfileController {
    @Autowired
    UserService userService;
    @GetMapping("api/loadPersonalProfile")
    public @ResponseBody String personalProfile(Model model) {
        if(((SessionVariables) model.getAttribute("sessionVariables")).myself == null)
            return null;
        // DA RIVEDEREEEE
        return ((SessionVariables) model.getAttribute("sessionVariables")).myself;
    }
    @GetMapping("api/loadProfile")
    public @ResponseBody String profile(Model model, @RequestParam(value = "username") String username) {
        SessionVariables sv = (SessionVariables) model.getAttribute("sessionVariables");
        UserDTO answer = userService.loadProfile(username, sv.myself);
        if(answer == null)
            return "no";
        return new Gson().toJson(answer);
    }
}

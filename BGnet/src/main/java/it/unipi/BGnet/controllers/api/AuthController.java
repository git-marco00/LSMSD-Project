package it.unipi.BGnet.controllers.api;

import it.unipi.BGnet.DTO.UserDTO;
import it.unipi.BGnet.Utilities.SessionVariables;
import it.unipi.BGnet.service.user.UserService;

import com.google.gson.Gson;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.sql.Timestamp;

@RestController
@SessionAttributes("sessionVariables")
public class AuthController {
    @Autowired
    UserService userService;
    @PostMapping("/api/login")
    public @ResponseBody String login(Model model, @RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {
        Gson gson = new Gson();
        UserDTO user = userService.getUser(username);
        if(user == null)
            return gson.toJson("{\"type\": 1, \"message\" : \"Incorrect username\"}");
        Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder();
        if(!pbkdf2PasswordEncoder.matches(password, user.getPassword()))
            return gson.toJson("{\"type\": 2, \"message\" : \"Incorrect password\"}");
        if((SessionVariables) model.getAttribute("sessionVariables") == null)
            model.addAttribute("sessionVariables", new SessionVariables());
        SessionVariables sv = (SessionVariables) model.getAttribute("sessionVariables");
        sv.myself = user.getUsername();
        sv.admin = userService.isAdmin(sv.myself);
        model.addAttribute("sessionVariables",  sv);
        return gson.toJson("{\"type\": 0, \"message\" : \"OK\"}");
    }
    @GetMapping("/api/isAdmin")
    public String isAdmin(Model model) {
        Gson gson = new Gson();
        if(((SessionVariables) model.getAttribute("sessionVariables")).admin)
            return gson.toJson(true);
        else
            return gson.toJson(false);
    }
    @GetMapping("/api/isLogged")
    public String isLogged(Model model) {
        Gson gson = new Gson();
        if(((SessionVariables) model.getAttribute("sessionVariables")).myself != null)
            return gson.toJson(true);
        else
            return gson.toJson(false);
    }
}

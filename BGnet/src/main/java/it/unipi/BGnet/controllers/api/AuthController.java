package it.unipi.BGnet.controllers.api;

import it.unipi.BGnet.DTO.UserDTO;
import it.unipi.BGnet.Utilities.SessionVariables;
import it.unipi.BGnet.service.user.UserService;

import com.google.gson.Gson;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;



@RestController
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

        ////////////////////////// ATTENZIONE DA DEBUGGARE //////////////////////////
        SessionVariables sv = (SessionVariables) model.getAttribute("sessionVariables");
        sv.myself = user.getUsername();
        /////////////////////////////////////////////////////////////////////////////

        return gson.toJson("{\"type\": 0, \"message\" : \"OK\"}");
    }
}

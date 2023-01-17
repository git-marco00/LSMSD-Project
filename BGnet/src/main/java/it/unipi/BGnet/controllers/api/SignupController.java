package it.unipi.BGnet.controllers.api;

import it.unipi.BGnet.model.User;
import it.unipi.BGnet.DTO.UserDTO;
import it.unipi.BGnet.service.user.UserService;

import com.google.gson.Gson;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.time.Year;

@RestController
public class SignupController {
    @Autowired
    UserService userService;
    @PostMapping("/api/signup")
    public String signup(Model model, @RequestParam(value = "firstname") String firstname, @RequestParam(value = "lastname") String lastname, @RequestParam(value = "username") String username, @RequestParam(value = "email") String email, @RequestParam(value = "password") String password, @RequestParam(value = "password2") String password2, @RequestParam(value = "state") String state, @RequestParam(value = "country") String country, @RequestParam(value = "continent") String continent) {
        Gson gson = new Gson();
        if(!password.equals(password2))
            return gson.toJson("{\"type\": 1, \"message\" : \"Passwords are different\"}");
        UserDTO result = userService.getUser(username);
        if(result != null)
            return gson.toJson("{\"type\": 2, \"message\" : \"Username already in use\"}");
        result = userService.getUserByEmail(email);
        if(result != null)
            return gson.toJson("{\"type\": 3, \"message\" : \"E-mail already in use\"}");
        Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder();
        User user = new User(username, pbkdf2PasswordEncoder.encode(password), firstname, lastname, Year.now().getValue(), email, state, country, continent);
        if(userService.addUser(user))
            return gson.toJson("{\"type\": 0, \"message\" : \"OK\"}");
        else return gson.toJson("{\"type\": 4, \"message\" : \"Something goes wrong\"}");
    }
}

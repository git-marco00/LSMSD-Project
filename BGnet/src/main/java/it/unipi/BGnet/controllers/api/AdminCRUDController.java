package it.unipi.BGnet.controllers.api;


import it.unipi.BGnet.service.user.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminCRUDController {
    @Autowired
    private ManagerService managerService;

    @PutMapping("/api/createGame")
    public boolean createGame(){return false;}

    @PutMapping("/api/deleteGame")
    public boolean deleteGame(){return false;}

    @PutMapping("/api/deletePost")
    public boolean deletePost(){return false;}

    @PutMapping("/api/deleteComment")
    public boolean deleteComment(){return false;}

    @PutMapping("/api/banUser")
    public boolean deleteUser(){return false;}
}

package it.unipi.BGnet.controllers.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LandingController {
    List<String> list;

    @RequestMapping("/api/landing")
    public Iterable<String> returnList(){
        // CHIAMO service
        // restituisco json {games: [{gamename: , gameurl}], users: [{username: , userurl}]}
        return null;
    }
}

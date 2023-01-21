package it.unipi.BGnet.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TournamentController {
    @RequestMapping("/tournamentPage")
    public String tournamentPage(){return "tournamentPage";}
}

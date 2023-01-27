package it.unipi.BGnet.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class SearchPageController {
    @RequestMapping("/searchPage")
    public String searchPage(){
        return "searchPage";
    }
}

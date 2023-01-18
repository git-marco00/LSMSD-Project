package it.unipi.BGnet.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommentPageController {
    @RequestMapping("/commentPage")
    public String commentPage(){
        return "commentPage";
    }
}

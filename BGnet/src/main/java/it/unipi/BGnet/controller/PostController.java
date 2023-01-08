package it.unipi.BGnet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class PostController {
    @GetMapping("/postPage")
    public String home()
    {
        return "postPage";
    }
}

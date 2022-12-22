package it.unipi.BGnet.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

@RestController
public class LandingController {
    List<String> list;

    @RequestMapping("/api/landing")
    public Iterable<String> returnList(){
        list = new ArrayList<String>();
        list.add("./img/image0.jpg");
        list.add("./img/image0.jpg");
        list.add("./img/image0.jpg");
        list.add("./img/image0.jpg");
        System.out.println("HO ESEGUITO");
        return list;
    }
}

package it.unipi.BGnet.controller.api;


import it.unipi.BGnet.Utilities.Constants;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/api/setButton1")
    public static void changeButton1(){
        Constants.label = "Cane";
    }

    @RequestMapping("/api/setButton2")
    public static void changeButton2(){
        Constants.label = "Gatto";
    }

    @RequestMapping("/api/getButton")
    public static String returnLabel(){
        return Constants.label;
    }
}

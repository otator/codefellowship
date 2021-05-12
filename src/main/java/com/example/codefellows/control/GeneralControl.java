package com.example.codefellows.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GeneralControl {

    @GetMapping("/")
    public String getSplashPage(){
        return "splash.html";
    }
}

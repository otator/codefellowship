package com.example.codefellows.control;

import com.example.codefellows.model.ApplicationUser;
import com.example.codefellows.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class GeneralControl {
    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @GetMapping("/")
    public String getSplashPage(Model model, Principal principal){
        ApplicationUser user = null;
        try {
            user = applicationUserRepository.findByUsername(principal.getName());
        }catch (Exception e){
            model.addAttribute("error", e.getMessage());
        }
        model.addAttribute("loggedUser", user);
        return "splash.html";
    }
}

package com.example.codefellows.control;

import com.example.codefellows.model.ApplicationUser;
import com.example.codefellows.model.Post;
import com.example.codefellows.repository.ApplicationUserRepository;
import com.example.codefellows.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class PostController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    ApplicationUserRepository applicationUserRepository;


    @GetMapping("/addPost")
    public String getPostForm(Model model, Principal principal){
        ApplicationUser user = applicationUserRepository.findByUsername(principal.getName());
        model.addAttribute("loggedUser", user);
        return "add_post.html";
    }
    @PostMapping("/addPost")
    public RedirectView addPost(String postBody, Principal principal){
        ApplicationUser user = applicationUserRepository.findByUsername(principal.getName());
        Post newPost = new Post(postBody, user);
        postRepository.save(newPost);
        return new RedirectView("/myprofile");
    }
}

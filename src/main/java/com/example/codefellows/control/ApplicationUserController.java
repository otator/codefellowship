package com.example.codefellows.control;

import com.example.codefellows.model.ApplicationUser;
import com.example.codefellows.model.Post;
import com.example.codefellows.repository.ApplicationUserRepository;
import com.example.codefellows.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;

@Controller
public class ApplicationUserController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    PostRepository postRepository;

    @GetMapping("/signup")
    public String getSignUpForm(){
        return "signup.html";
    }

    @PostMapping("/signup")
    public RedirectView signUpUser(String username, String password, String firstName, String lastName, String dob, String bio){
        ApplicationUser newUser = new ApplicationUser(username, bCryptPasswordEncoder.encode(password), firstName, lastName, dob, bio);
        applicationUserRepository.save(newUser);
        return new RedirectView("/");
    }

    @GetMapping("/users/{id}")
    public String getUserInfo(@PathVariable Long id, Model model, Principal principal){
        ApplicationUser user = applicationUserRepository.findById(id).get();
        model.addAttribute("user",user);
        ApplicationUser loggedInUser = null;
        try {
            loggedInUser = applicationUserRepository.findByUsername(principal.getName());
        }catch (Exception e){
            model.addAttribute("error", e.getMessage());
        }
        model.addAttribute("loggedUser", loggedInUser);
        return "user.html";
    }

    @GetMapping("/login")
    public String getLoginForm(){
        return "login.html";
    }

    @GetMapping("/myprofile")
    public String showProfile(Model model, Principal principal){
        ApplicationUser user = null;
        List<Post> posts = null;

        try {
            System.out.println("inside try");
            user = applicationUserRepository.findByUsername(principal.getName());
        }catch (Exception e){
            model.addAttribute("error", e.getMessage());
        }
        model.addAttribute("user", user);
        return "profile.html";
    }

    @GetMapping("/discover")
    public String getAllUsers(Model model, Principal principal){
        ApplicationUser currentUser = applicationUserRepository.findByUsername(principal.getName());
        List<ApplicationUser> allUsers = applicationUserRepository.findAll();
        allUsers.remove(currentUser);
        model.addAttribute("users", allUsers);
        model.addAttribute("loggedUser", currentUser);
        return "discover.html";
    }

    @GetMapping("/feed")
    public String getFeed(Principal principal, Model model){
        ApplicationUser currentUser = applicationUserRepository.findByUsername(principal.getName());
        List<Post> posts = postRepository.findFollowingPosts(currentUser.getUsername());

        model.addAttribute("loggedUser", currentUser.getUsername());
        model.addAttribute("posts", posts);
        return "feed.html";
    }

    @GetMapping("/follow/{username}")
    public RedirectView followUser(@PathVariable String username, Principal principal){
        ApplicationUser currentUser = applicationUserRepository.findByUsername(principal.getName());
        ApplicationUser userToFollow = applicationUserRepository.findByUsername(username);
        currentUser.addFollower(userToFollow);
        applicationUserRepository.save(currentUser);
        return new RedirectView("/profile/" + username);
    }

    @GetMapping("/unfollow/{username}")
    public RedirectView unFollowUser(@PathVariable String username, Principal principal){
        ApplicationUser currentUser = applicationUserRepository.findByUsername(principal.getName());
        ApplicationUser userToFollow = applicationUserRepository.findByUsername(username);
        currentUser.removeFollower(userToFollow);
        applicationUserRepository.save(currentUser);
        return new RedirectView("/profile/" + username);
    }

    @GetMapping("/profile/{username}")
    public String getUserProfile(@PathVariable String username, Model model, Principal principal){
        ApplicationUser user = applicationUserRepository.findByUsername(username);
        ApplicationUser currentUser = applicationUserRepository.findByUsername(principal.getName());
        model.addAttribute("user",user);
        model.addAttribute("loggedUser", currentUser);
        return "profile.html";
    }
}

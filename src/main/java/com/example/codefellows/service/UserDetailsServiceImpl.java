package com.example.codefellows.service;

import com.example.codefellows.model.ApplicationUser;
import com.example.codefellows.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        ApplicationUser userApplication = applicationUserRepository.findByUsername(username);

        if (userApplication == null) {
            System.out.println("unable to find user");
            throw new UsernameNotFoundException("can't find user:  " + username);
        }
        System.out.println("User " + username + " found");
        return userApplication;
    }
}

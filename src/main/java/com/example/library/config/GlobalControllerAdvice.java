package com.example.library.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute
    public void addUserRoles(Model model, Authentication authentication) {
        if (authentication != null) {
            model.addAttribute("isAdmin", authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
            model.addAttribute("isStudent", authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_STUDENT")));
        }
    }
}

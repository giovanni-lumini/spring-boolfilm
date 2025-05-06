package org.finalproject.spring.boolfilm.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping
    public String home(Model model, Authentication authentication) {
        // authetication name
        model.addAttribute("username", authentication.getName());
        return "home";
    }

}

package com.julant.skillang.controllers;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/messages")
    public String app() {
        return "messages";
    }

    @GetMapping("/settings")
    public String settings() {
        return "settings";
    }

    @GetMapping("/profile")
    public String profile() {
        return "profile/view";
    }

    @GetMapping("/profile/edit")
    public String profileEdit() {
        return "profile/edit";
    }

    @GetMapping("/finder")
    public String finder() {
        return "finder";
    }

    @GetMapping("/friends")
    public String friends() {
        return "friends/view";
    }

    @GetMapping("/friends/pending")
    public String pending() {
        return "friends/pending";
    }

    @GetMapping("/friends/outgoing")
    public String outgoing() {
        return "/friends/outgoing";
    }

    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }
}

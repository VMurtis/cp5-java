package br.com.fiap.cp5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {


    // link: http://localhost:8080/login
    @GetMapping("/login")
    public String showLoginPage() {
        return "redirect:/login.html";
    }

    // link: http://localhost:8080/
    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/login.html";
    }
}

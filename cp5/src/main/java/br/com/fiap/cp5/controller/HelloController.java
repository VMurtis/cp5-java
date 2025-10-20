package br.com.fiap.cp5.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    // link: http://localhost:8080/publico/hello
    @GetMapping("/publico/hello")
    public String helloPublico() {
        return "<h1>Endpoint público - não precisa de login!</h1>";
    }
}

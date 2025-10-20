package br.com.fiap.cp5.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seguro")
public class SecuredController {

    // link: http://localhost:8080/seguro/hello
    @GetMapping("/hello")
    public String hello() {
        return "Endpoint protegido - você está autenticado! Tranquilo!";
    }
}
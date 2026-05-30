package com.leo.fullstackspec_apirestdemo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController // Equivalente ao @Controller na classe + @ResponseBody nos métodos
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/{name}")
    public String get(@PathVariable String name) {
        return "Olá, " + name + "!";
    }

    @PostMapping("/world/{value}")
    @ResponseStatus(HttpStatus.CREATED)
    public String post(@PathVariable String value) {
        return "Received: " + value;
    }
}

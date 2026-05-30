package com.leo.fullstackspec_apirestdemo.controllers;

import com.leo.fullstackspec_apirestdemo.dtos.TemperaturaResponse;
import com.leo.fullstackspec_apirestdemo.services.TemperaturaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temperatura")
@AllArgsConstructor
public class TemperaturaController {

    private final TemperaturaService service;

    // Parâmetros required=false para realizar validações no service (ideal seria usar o Jakarta Validation)
    @GetMapping("/convert")
    public TemperaturaResponse convert(
            @RequestParam(required = false) String de,
            @RequestParam(required = false) String para,
            @RequestParam(required = false) Double valor
    ) {
        return service.convert(de, para, valor);
    }
}

package com.leo.fullstackspec_apirestdemo.controllers;

import com.leo.fullstackspec_apirestdemo.dtos.TemperaturaResponse;
import com.leo.fullstackspec_apirestdemo.services.TemperaturaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    // Parâmetros required=false para realizar validações no service (ideal seria usar o Jakarta Validation)
    @GetMapping("/convert/{valor}/de/{de}/para/{para}")
    public TemperaturaResponse convertFromPath(
            @PathVariable(required = false) String de,
            @PathVariable(required = false) String para,
            @PathVariable(required = false) Double valor
    ) {
        return service.convert(de, para, valor);
    }

    // Parâmetros required=false para realizar validações no service (ideal seria usar o Jakarta Validation)
    @GetMapping("/convert/{valor}/{de}/{para}")
    public TemperaturaResponse convertFromPathFromExercise(
            @PathVariable(required = false) String de,
            @PathVariable(required = false) String para,
            @PathVariable(required = false) Double valor
    ) {
        return service.convert(de, para, valor);
    }
}

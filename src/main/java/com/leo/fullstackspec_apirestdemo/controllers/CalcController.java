package com.leo.fullstackspec_apirestdemo.controllers;

import com.leo.fullstackspec_apirestdemo.dtos.CalcResponse;
import com.leo.fullstackspec_apirestdemo.services.CalcService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calc")
@AllArgsConstructor
public class CalcController {

    private final CalcService service;

    // Parâmetros required=false para realizar validações no service (ideal seria usar o Jakarta Validation)
    @GetMapping("/soma")
    public CalcResponse sumByQueryParams(
            @RequestParam(required = false) Double a,
            @RequestParam(required = false) Double b
    ) {
        return service.soma(a, b);
    }

    // Parâmetros required=false para realizar validações no service (ideal seria usar o Jakarta Validation)
    @GetMapping("/soma/{a}/{b}")
    public CalcResponse sumByPath(
            @PathVariable(required = false) Double a,
            @PathVariable(required = false) Double b
    ) {
        return service.soma(a, b);
    }

    // Parâmetros required=false para realizar validações no service (ideal seria usar o Jakarta Validation)
    @PostMapping("/soma/{a}")
    @ResponseStatus(HttpStatus.CREATED)
    public CalcResponse sumByPathAndBody(
            @PathVariable(required = false) Double a,
            @RequestBody(required = false) Double b
    ) {
        return service.soma(a, b);
    }
}

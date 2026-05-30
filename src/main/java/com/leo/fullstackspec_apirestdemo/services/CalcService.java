package com.leo.fullstackspec_apirestdemo.services;

import com.leo.fullstackspec_apirestdemo.dtos.CalcResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CalcService {
    public CalcResponse soma(Double a, Double b) {
        validateParams(a, b);
        return new CalcResponse(a + b);
    }

    private void validateParams(Double a, Double b) {
        if (a == null || b == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "É necessário indicar os parâmetros \"a\" e \"b\""
            );
        }
    }
}

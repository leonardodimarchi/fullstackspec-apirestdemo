package com.leo.fullstackspec_apirestdemo.services;

import com.leo.fullstackspec_apirestdemo.dtos.TemperaturaResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TemperaturaService {

    public TemperaturaResponse convert(String de, String para, Double valor) {
        validateParams(de, para, valor);

        de = de.toUpperCase();
        para = para.toUpperCase();

        validateUnit(de);
        validateUnit(para);

        double result = convertTemperature(de, para, valor);

        return new TemperaturaResponse(para, result);
    }

    private void validateParams(String de, String para, Double valor) {
        if (de == null || para == null || valor == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "É necessário indicar os parâmetros \"de\" e \"para\" e \"valor\""
            );
        }
    }

    private void validateUnit(String unidade) {
        if (!List.of("C", "F", "K").contains(unidade)) {
            throw new IllegalArgumentException(
                    "Unidade inválida, utilize apenas C, F ou K."
            );
        }
    }

    private double convertTemperature(
            String de,
            String para,
            double valor
    ) {
        if (de.equals(para)) {
            return valor;
        }

        double celsius;

        switch (de) {
            case "F" -> celsius = (valor - 32) * 5 / 9;
            case "K" -> celsius = valor - 273.15;
            default -> celsius = valor;
        }

        return switch (para) {
            case "F" -> celsius * 9 / 5 + 32;
            case "K" -> celsius + 273.15;
            default -> valor;
        };
    }
}

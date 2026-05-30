package com.leo.fullstackspec_apirestdemo.controllers;

import com.leo.fullstackspec_apirestdemo.dtos.PersonRequest;
import com.leo.fullstackspec_apirestdemo.entities.PersonEntity;
import com.leo.fullstackspec_apirestdemo.services.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoas")
@AllArgsConstructor
public class PersonController {

    private final PersonService service;

    @GetMapping()
    public List<PersonEntity> findAll() {
        return service.findAll();
    }

    @GetMapping("/{name}")
    public PersonEntity findByName(@PathVariable String name) {
        return service.findByNameOrFail(name);
    }

    @PostMapping
    public PersonEntity post(
            @RequestBody PersonRequest payload
    ) {
        return service.create(payload);
    }

    @PutMapping("/{id}")
    public PersonEntity put(
            @PathVariable Long id,
            @RequestBody PersonRequest payload
    ) {
        return service.put(id, payload);
    }

    @PatchMapping("/{id}")
    public PersonEntity patch(
            @PathVariable Long id,
            @RequestBody PersonRequest payload
    ) {
        return service.patch(id, payload);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

package com.leo.fullstackspec_apirestdemo.services;

import com.leo.fullstackspec_apirestdemo.dtos.PersonRequest;
import com.leo.fullstackspec_apirestdemo.entities.PersonEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private static final List<PersonEntity> persons = new ArrayList<>();

    public PersonEntity create(PersonRequest payload) {
        PersonEntity entity = PersonEntity.builder()
                .id(generateNewId())
                .name(payload.name())
                .age(payload.age())
                .build();

        persons.add(entity);

        return entity;
    }

    public PersonEntity patch(Long id, PersonRequest payload) {
        PersonEntity existing = findByIdOrFail(id);

        PersonEntity updated = PersonEntity.builder()
                .id(id)
                .name(payload.name() != null ? payload.name() : existing.getName())
                .age(payload.age() != null ? payload.age() : existing.getAge())
                .build();

        persons.removeIf(person -> person.getId().equals(id));
        persons.add(updated);

        return updated;
    }

    public PersonEntity put(Long id, PersonRequest payload) {
        PersonEntity updated = PersonEntity.builder()
                .id(payload.id())
                .name(payload.name())
                .age(payload.age())
                .build();

        persons.removeIf(person -> person.getId().equals(id));
        persons.add(updated);

        return updated;
    }

    public List<PersonEntity> findAll() {
        return persons;
    }

    public void delete(Long id) {
        persons.removeIf(person -> person.getId().equals(id));
    }

    public PersonEntity findByIdOrFail(Long id) {
        return persons.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Pessoa não encontrada."
                ));
    }

    public PersonEntity findByNameOrFail(String name) {
        return persons.stream()
                .filter(person -> person.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Pessoa não encontrada."
                ));
    }

    private Long generateNewId() {
        return persons.stream()
                .map(PersonEntity::getId)
                .max(Long::compareTo)
                .orElse(0L) + 1;
    }
}

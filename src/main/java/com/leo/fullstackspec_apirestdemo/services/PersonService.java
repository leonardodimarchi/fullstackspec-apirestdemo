package com.leo.fullstackspec_apirestdemo.services;

import com.leo.fullstackspec_apirestdemo.dtos.PersonRequest;
import com.leo.fullstackspec_apirestdemo.entities.PersonEntity;
import com.leo.fullstackspec_apirestdemo.repositories.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonService {

    private final PersonRepository repository;

    public PersonEntity create(PersonRequest payload) {
        PersonEntity entity = PersonEntity.builder()
                .name(payload.name())
                .age(payload.age())
                .build();

        return repository.save(entity);
    }

    public PersonEntity patch(Long id, PersonRequest payload) {
        PersonEntity existing = findByIdOrFail(id);

        if (payload.name() != null) {
            existing.setName(payload.name());
        }

        if (payload.age() != null) {
            existing.setAge(payload.age());
        }

        return repository.save(existing);
    }

    public PersonEntity put(Long id, PersonRequest payload) {
        PersonEntity existing = findByIdOrFail(id);

        existing.setName(payload.name());
        existing.setAge(payload.age());

        return repository.save(existing);
    }

    public List<PersonEntity> findAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public PersonEntity findByIdOrFail(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Pessoa não encontrada."
                ));
    }
}

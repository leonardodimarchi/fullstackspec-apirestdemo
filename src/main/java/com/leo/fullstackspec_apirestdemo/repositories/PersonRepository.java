package com.leo.fullstackspec_apirestdemo.repositories;

import com.leo.fullstackspec_apirestdemo.entities.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long>  {
    public Optional<PersonEntity> findByName(String name);
}

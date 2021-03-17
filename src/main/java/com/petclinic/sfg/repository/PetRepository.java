package com.petclinic.sfg.repository;

import com.petclinic.sfg.model.Pet;
import org.springframework.data.repository.Repository;

import java.util.Collection;
import java.util.Optional;

public interface PetRepository extends Repository<Pet, Integer>{

    Optional<Pet> findById(int id);

    void save(Pet pet);

    Collection<Pet> findAll();

    void delete(Pet pet);

}

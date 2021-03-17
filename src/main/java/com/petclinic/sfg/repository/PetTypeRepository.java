package com.petclinic.sfg.repository;

import com.petclinic.sfg.model.PetType;
import org.springframework.data.repository.Repository;

import java.util.Collection;
import java.util.Optional;

public interface PetTypeRepository extends Repository<PetType, Integer> {

    Optional<PetType> findById(int id);

    Collection<PetType> findAll();

    void save(PetType petType);

    void delete(PetType petType);

}

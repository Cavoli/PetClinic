package com.petclinic.sfg.repository;

import com.petclinic.sfg.model.Specialty;
import org.springframework.data.repository.Repository;

import java.util.Collection;
import java.util.Optional;

public interface SpecialtyRepository extends Repository<Specialty, Integer>{

    Optional<Specialty> findById(int id);

    Collection<Specialty> findAll();

    void save(Specialty specialty);

    void delete(Specialty specialty);

}

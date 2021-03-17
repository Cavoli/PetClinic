package com.petclinic.sfg.repository;

import com.petclinic.sfg.model.Visit;
import org.springframework.data.repository.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface VisitRepository extends Repository<Visit, Integer> {

    void save(Visit visit);

    List<Visit> findByPetId(Integer petId);

    Optional<Visit> findById(int id);

    Collection<Visit> findAll();

    void delete(Visit visit);

}

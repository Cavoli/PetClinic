package com.petclinic.sfg.repository;


import com.petclinic.sfg.model.Vet;
import org.springframework.data.repository.Repository;

import java.util.Collection;
import java.util.Optional;

public interface VetRepository extends Repository<Vet, Integer> {

    Collection<Vet> findAll();

    Optional<Vet> findById(int id);

    void save(Vet vet);

    void delete(Vet vet);


}

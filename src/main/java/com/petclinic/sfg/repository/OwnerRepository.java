package com.petclinic.sfg.repository;

import com.petclinic.sfg.model.Owner;
import org.springframework.data.repository.Repository;

import java.util.Collection;
import java.util.Optional;

public interface OwnerRepository extends Repository<Owner, Integer> {

    Collection<Owner> findByLastName(String lastName);

    Optional<Owner> findById(int id);

    void save(Owner owner);

    Collection<Owner> findAll();

    void delete(Owner owner);


}

package com.petclinic.sfg.repository.implementation;

import com.petclinic.sfg.model.Owner;
import com.petclinic.sfg.repository.OwnerRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface OwnerRepositoryImpl extends OwnerRepository {

    @Override
    @Query("SELECT DISTINCT owner FROM Owner owner left join fetch owner.pets WHERE owner.lastName LIKE :lastName%")
    Collection<Owner> findByLastName(@Param("lastName") String lastName);

}

package com.petclinic.sfg.service;

import com.petclinic.sfg.model.Owner;

import java.util.Collection;

public interface OwnerService {

    Owner findOwnerById(int id);

    Collection<Owner> findAllOwners();

    void saveOwner(Owner owner);

    void deleteOwner(Owner owner);

    Collection<Owner> findOwnerByLastName(String lastName);

}

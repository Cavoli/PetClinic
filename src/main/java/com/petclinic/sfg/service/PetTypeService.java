package com.petclinic.sfg.service;

import com.petclinic.sfg.model.PetType;

import java.util.Collection;

public interface PetTypeService {

    PetType findPetTypeById(int petTypeId);

    Collection<PetType> findAllPetTypes();

    void savePetType(PetType petType);

    void deletePetType(PetType petType);

}

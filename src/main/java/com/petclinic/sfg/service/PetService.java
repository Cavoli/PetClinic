package com.petclinic.sfg.service;

import com.petclinic.sfg.model.Pet;
import com.petclinic.sfg.model.PetType;

import java.util.Collection;

public interface PetService {

    Pet findPetById(int id);

    Collection<Pet> findAllPets();

    void savePet(Pet pet);

    void deletePet(Pet pet);
}

package com.petclinic.sfg.service.impl;

import com.petclinic.sfg.model.Pet;
import com.petclinic.sfg.repository.PetRepository;
import com.petclinic.sfg.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    @Autowired
    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Pet findPetById(int id) {
        return petRepository.findById(id).orElse(null);
    }

    @Override
    public Collection<Pet> findAllPets() {
        return petRepository.findAll();
    }

    @Override
    public void savePet(Pet pet) {
        petRepository.save(pet);
    }

    @Override
    public void deletePet(Pet pet) {
        petRepository.delete(pet);
    }
}

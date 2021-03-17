package com.petclinic.sfg.service.impl;

import com.petclinic.sfg.model.PetType;
import com.petclinic.sfg.repository.PetTypeRepository;
import com.petclinic.sfg.service.PetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class PetTypeServiceImpl implements PetTypeService {

    private final PetTypeRepository petTypeRepository;

    @Autowired
    public PetTypeServiceImpl(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public PetType findPetTypeById(int petTypeId) {
        return petTypeRepository.findById(petTypeId).orElse(null);
    }

    @Override
    public Collection<PetType> findAllPetTypes() {
        return petTypeRepository.findAll();
    }

    @Override
    public void savePetType(PetType petType) {
        petTypeRepository.save(petType);
    }

    @Override
    public void deletePetType(PetType petType) {
        petTypeRepository.delete(petType);
    }

}

package com.petclinic.sfg.service.impl;

import com.petclinic.sfg.model.Specialty;
import com.petclinic.sfg.repository.SpecialtyRepository;
import com.petclinic.sfg.service.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class SpecialtyServiceImpl implements SpecialtyService {

    private final SpecialtyRepository specialtyRepository;

    @Autowired
    public SpecialtyServiceImpl(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public Specialty findSpecialtyById(int specialtyId) {
        return specialtyRepository.findById(specialtyId).orElse(null);
    }

    @Override
    public Collection<Specialty> findAllSpecialties() {
        return specialtyRepository.findAll();
    }

    @Override
    public void saveSpecialty(Specialty specialty) {
        specialtyRepository.save(specialty);
    }

    @Override
    public void deleteSpecialty(Specialty specialty) {
        specialtyRepository.delete(specialty);
    }
}

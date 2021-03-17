package com.petclinic.sfg.service;

import com.petclinic.sfg.model.Specialty;

import java.util.Collection;

public interface SpecialtyService {

    Specialty findSpecialtyById(int specialtyId);

    Collection<Specialty> findAllSpecialties();

    void saveSpecialty(Specialty specialty);

    void deleteSpecialty(Specialty specialty);

}

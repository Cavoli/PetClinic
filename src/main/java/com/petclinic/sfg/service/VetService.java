package com.petclinic.sfg.service;

import com.petclinic.sfg.model.Vet;

import java.util.Collection;

public interface VetService {

    Vet findVetById(int id);

    Collection<Vet> findAllVets();

    void saveVet(Vet vet);

    void deleteVet(Vet vet);
}

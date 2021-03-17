package com.petclinic.sfg.service.impl;

import com.petclinic.sfg.model.Vet;
import com.petclinic.sfg.repository.VetRepository;
import com.petclinic.sfg.service.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class VetServiceImpl implements VetService {

    private final VetRepository vetRepository;

    @Autowired
    public VetServiceImpl(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Override
    public Vet findVetById(int id) {
        return vetRepository.findById(id).orElse(null);
    }

    @Override
    public Collection<Vet> findAllVets() {
        return vetRepository.findAll();
    }

    @Override
    public void saveVet(Vet vet) {
        vetRepository.save(vet);
    }

    @Override
    public void deleteVet(Vet vet) {
        vetRepository.delete(vet);
    }
}

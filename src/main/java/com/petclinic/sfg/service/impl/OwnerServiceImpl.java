package com.petclinic.sfg.service.impl;

import com.petclinic.sfg.model.Owner;
import com.petclinic.sfg.repository.OwnerRepository;
import com.petclinic.sfg.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    @Autowired
    public OwnerServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Owner findOwnerById(int id) {
        return ownerRepository.findById(id).orElse(null);
    }

    @Override
    public Collection<Owner> findAllOwners() {
        return ownerRepository.findAll();
    }


    @Override
    public void saveOwner(Owner owner) {
        ownerRepository.save(owner);

    }

    @Override
    public Collection<Owner> findOwnerByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }

    @Override
    public void deleteOwner(Owner owner) {
        ownerRepository.delete(owner);
    }

}

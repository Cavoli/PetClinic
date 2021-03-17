package com.petclinic.sfg.controller;

import com.petclinic.sfg.model.Pet;
import com.petclinic.sfg.model.PetType;
import com.petclinic.sfg.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("api/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping(value = "/{petId}", produces = "application/json")
    public ResponseEntity<Pet> getPet(@PathVariable("petId") int petId) {
        Pet pet = this.petService.findPetById(petId);
        if (pet == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pet, HttpStatus.OK);
    }

    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<Collection<Pet>> getPets() {
        Collection<Pet> pets = this.petService.findAllPets();
        if (pets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pets, HttpStatus.OK);
    }

    @PostMapping(value = "", produces = "application/json")
    public ResponseEntity<Pet> addPet(@RequestBody @Valid Pet pet,
                                      BindingResult bindingResult, UriComponentsBuilder ucBuilder) {
        HttpHeaders headers = new HttpHeaders();
        if (bindingResult.hasErrors() || (pet == null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);     //TO BE EXPANDED
        }
        this.petService.savePet(pet);
        headers.setLocation(ucBuilder.path("/api/pets/{id}").buildAndExpand(pet.getId()).toUri());
        return new ResponseEntity<>(pet, headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{petId}", produces = "application/json")
    public ResponseEntity<Pet> updatePet(@PathVariable("petId") int petId,
                                         @RequestBody @Valid Pet pet, BindingResult bindingResult) {
        if (bindingResult.hasErrors() || (pet == null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);     //TO BE EXPANDED
        }
        Pet currentPet = this.petService.findPetById(petId);
        if (currentPet == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentPet.setBirthDate(pet.getBirthDate());
        currentPet.setName(pet.getName());
        currentPet.setType(pet.getType());
        currentPet.setOwner(pet.getOwner());
        this.petService.savePet(currentPet);
        return new ResponseEntity<Pet>(currentPet, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{petId}", produces = "application/json")
    public ResponseEntity<Void> deletePet(@PathVariable("petId") int petId) {
        Pet pet = this.petService.findPetById(petId);
        if (pet == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.petService.deletePet(pet);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}

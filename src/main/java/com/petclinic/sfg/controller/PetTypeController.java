package com.petclinic.sfg.controller;

import com.petclinic.sfg.model.PetType;
import com.petclinic.sfg.service.PetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("api/pettypes")
public class PetTypeController {

	@Autowired
	private PetTypeService petTypeService;

	@GetMapping(value = "", produces = "application/json")
	public ResponseEntity<Collection<PetType>> getAllPetTypes(){
		Collection<PetType> petTypes = new ArrayList<>();
		petTypes.addAll(this.petTypeService.findAllPetTypes());
		if (petTypes.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(petTypes, HttpStatus.OK);
	}

	@GetMapping(value = "/{petTypeId}", produces = "application/json")
	public ResponseEntity<PetType> getPetType(@PathVariable("petTypeId") int petTypeId){
		PetType petType = this.petTypeService.findPetTypeById(petTypeId);
		if(petType == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(petType, HttpStatus.OK);
	}

	@PostMapping(value = "", produces = "application/json")
	public ResponseEntity<PetType> addPetType(@RequestBody @Valid PetType petType, BindingResult bindingResult, UriComponentsBuilder ucBuilder){
		HttpHeaders headers = new HttpHeaders();
		if(bindingResult.hasErrors() || (petType == null)){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);     //TO BE EXPANDED
		}
		this.petTypeService.savePetType(petType);
		headers.setLocation(ucBuilder.path("/api/pettypes/{id}").buildAndExpand(petType.getId()).toUri());
		return new ResponseEntity<>(petType, headers, HttpStatus.CREATED);
	}

	@PutMapping(value = "/{petTypeId}", produces = "application/json")
	public ResponseEntity<PetType> updatePetType(@PathVariable("petTypeId") int petTypeId,
												 @RequestBody @Valid PetType petType, BindingResult bindingResult){
		if(bindingResult.hasErrors() || (petType == null)){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);     //TO BE EXPANDED
		}
		PetType currentPetType = this.petTypeService.findPetTypeById(petTypeId);
		if(currentPetType == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		currentPetType.setName(petType.getName());
		this.petTypeService.savePetType(currentPetType);
		return new ResponseEntity<PetType>(currentPetType, HttpStatus.NO_CONTENT);
	}

	@DeleteMapping(value = "/{petTypeId}", produces = "application/json")
	public ResponseEntity<Void> deletePetType(@PathVariable("petTypeId") int petTypeId){
		PetType petType = this.petTypeService.findPetTypeById(petTypeId);
		if(petType == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		this.petTypeService.deletePetType(petType);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}

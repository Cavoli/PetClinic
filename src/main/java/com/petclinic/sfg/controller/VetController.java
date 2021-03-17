package com.petclinic.sfg.controller;

import com.petclinic.sfg.model.Specialty;
import com.petclinic.sfg.model.Vet;
import com.petclinic.sfg.service.VetService;
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
@RequestMapping("api/vets")
public class VetController {

	@Autowired
	private VetService vetService;

	@GetMapping(value = "", produces = "application/json")
	public ResponseEntity<Collection<Vet>> getAllVets(){
		Collection<Vet> vets = new ArrayList<>();
		vets.addAll(this.vetService.findAllVets());
		if (vets.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(vets, HttpStatus.OK);
	}

	@GetMapping(value = "/{vetId}", produces = "application/json")
	public ResponseEntity<Vet> getVet(@PathVariable("vetId") int vetId){
		Vet vet = this.vetService.findVetById(vetId);
		if(vet == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(vet, HttpStatus.OK);
	}

	@PostMapping(value = "", produces = "application/json")
	public ResponseEntity<Vet> addVet(@RequestBody @Valid Vet vet,
									  BindingResult bindingResult, UriComponentsBuilder ucBuilder){
		HttpHeaders headers = new HttpHeaders();
		if(bindingResult.hasErrors() || (vet == null)){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);     //TO BE EXPANDED
		}
		this.vetService.saveVet(vet);
		headers.setLocation(ucBuilder.path("/api/vets/{id}").buildAndExpand(vet.getId()).toUri());
		return new ResponseEntity<>(vet, headers, HttpStatus.CREATED);
	}

	@PutMapping(value = "/{vetId}", produces = "application/json")
	public ResponseEntity<Vet> updateVet(@PathVariable("vetId") int vetId, @RequestBody @Valid Vet vet, BindingResult bindingResult){
		if(bindingResult.hasErrors() || (vet == null)){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);     //TO BE EXPANDED
		}
		Vet currentVet = this.vetService.findVetById(vetId);
		if(currentVet == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		currentVet.setFirstName(vet.getFirstName());
		currentVet.setLastName(vet.getLastName());
		currentVet.getSpecialties().clear();
		for(Specialty specialty : vet.getSpecialties()){
			currentVet.getSpecialties().add(specialty);
		}
		this.vetService.saveVet(currentVet);
		return new ResponseEntity<>(currentVet, HttpStatus.NO_CONTENT);
	}

	@DeleteMapping(value = "/{vetId}", produces = "application/json")
	public ResponseEntity<Void> deleteVet(@PathVariable("vetId") int vetId){
		Vet vet = this.vetService.findVetById(vetId);
		if(vet == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		this.vetService.deleteVet(vet);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}



}

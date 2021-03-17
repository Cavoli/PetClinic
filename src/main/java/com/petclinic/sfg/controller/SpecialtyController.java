package com.petclinic.sfg.controller;

import com.petclinic.sfg.model.Specialty;
import com.petclinic.sfg.service.SpecialtyService;
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
@RequestMapping("api/specialties")
public class SpecialtyController {

	@Autowired
	private SpecialtyService specialtyService;

	@GetMapping(value = "", produces = "application/json")
	public ResponseEntity<Collection<Specialty>> getAllSpecialtys(){
		Collection<Specialty> specialties;
		specialties = new ArrayList<Specialty>();
		specialties.addAll(this.specialtyService.findAllSpecialties());
		if (specialties.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(specialties, HttpStatus.OK);
	}

	@GetMapping(value = "/{specialtyId}", produces = "application/json")
	public ResponseEntity<Specialty> getSpecialty(@PathVariable("specialtyId") int specialtyId){
		Specialty specialty = this.specialtyService.findSpecialtyById(specialtyId);
		if(specialty == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(specialty, HttpStatus.OK);
	}

	@PostMapping(value = "", produces = "application/json")
	public ResponseEntity<Specialty> addSpecialty(@RequestBody @Valid Specialty specialty, BindingResult bindingResult, UriComponentsBuilder ucBuilder){
		HttpHeaders headers = new HttpHeaders();
		if(bindingResult.hasErrors() || (specialty == null)){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);     //TO BE EXPANDED
		}
		this.specialtyService.saveSpecialty(specialty);
		headers.setLocation(ucBuilder.path("/api/specialtys/{id}").buildAndExpand(specialty.getId()).toUri());
		return new ResponseEntity<>(specialty, headers, HttpStatus.CREATED);
	}

	@PutMapping(value = "/{specialtyId}", produces = "application/json")
	public ResponseEntity<Specialty> updateSpecialty(@PathVariable("specialtyId") int specialtyId,
													 @RequestBody @Valid Specialty specialty, BindingResult bindingResult){
		if(bindingResult.hasErrors() || (specialty == null)){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);     //TO BE EXPANDED
		}
		Specialty currentSpecialty = this.specialtyService.findSpecialtyById(specialtyId);
		if(currentSpecialty == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		currentSpecialty.setName(specialty.getName());
		this.specialtyService.saveSpecialty(currentSpecialty);
		return new ResponseEntity<>(currentSpecialty, HttpStatus.NO_CONTENT);
	}

	@DeleteMapping(value = "/{specialtyId}", produces = "application/json")
	public ResponseEntity<Void> deleteSpecialty(@PathVariable("specialtyId") int specialtyId){
		Specialty specialty = this.specialtyService.findSpecialtyById(specialtyId);
		if(specialty == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		this.specialtyService.deleteSpecialty(specialty);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}

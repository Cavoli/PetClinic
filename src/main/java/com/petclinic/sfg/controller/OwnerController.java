package com.petclinic.sfg.controller;

import com.petclinic.sfg.model.Owner;
import com.petclinic.sfg.service.OwnerService;
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
@RequestMapping("/api/owners")
public class OwnerController {

	@Autowired
	private OwnerService ownerService;

	@GetMapping(value = "/lastname/{lastName}", produces = "application/json")
	public ResponseEntity<Collection<Owner>> getOwnersList(@PathVariable("lastName") String ownerLastName) {
		if (ownerLastName == null) {
			ownerLastName = "";
		}
		Collection<Owner> owners = this.ownerService.findOwnerByLastName(ownerLastName);
		if (owners.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(owners, HttpStatus.OK);
	}

	@GetMapping(value = "", produces = "application/json")
	public ResponseEntity<Collection<Owner>> getOwners() {
		Collection<Owner> owners = this.ownerService.findAllOwners();
		if (owners.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(owners, HttpStatus.OK);
	}

	@GetMapping(value = "/{ownerId}", produces = "application/json")
	public ResponseEntity<Owner> getOwner(@PathVariable("ownerId") int ownerId) {
		Owner owner = null;
		owner = this.ownerService.findOwnerById(ownerId);
		if (owner == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(owner, HttpStatus.OK);
	}

	@PostMapping(value = "", produces = "application/json")
	public ResponseEntity<Owner> addOwner(@RequestBody @Valid Owner owner, BindingResult bindingResult,
			UriComponentsBuilder ucBuilder) {
		HttpHeaders headers = new HttpHeaders();
		if (bindingResult.hasErrors() || owner.getId() != null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);    //TO BE EXPANDED
		}
		this.ownerService.saveOwner(owner);
		headers.setLocation(ucBuilder.path("/api/owners/{id}").buildAndExpand(owner.getId()).toUri());
		return new ResponseEntity<>(owner, headers, HttpStatus.CREATED);
	}

	@PutMapping(value = "/{ownerId}", produces = "application/json")
	public ResponseEntity<Owner> updateOwner(@PathVariable("ownerId") int ownerId, @RequestBody @Valid Owner owner,
			BindingResult bindingResult) {
	    boolean bodyIdMatchesPathId = owner.getId() == null || ownerId == owner.getId();
		if (bindingResult.hasErrors() || !bodyIdMatchesPathId) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);   //TO BE EXPANDED
		}
		Owner currentOwner = this.ownerService.findOwnerById(ownerId);
		if (currentOwner == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		currentOwner.setAddress(owner.getAddress());
		currentOwner.setCity(owner.getCity());
		currentOwner.setFirstName(owner.getFirstName());
		currentOwner.setLastName(owner.getLastName());
		currentOwner.setTelephone(owner.getTelephone());
		this.ownerService.saveOwner(currentOwner);
		return new ResponseEntity<>(currentOwner, HttpStatus.NO_CONTENT);
	}

	@DeleteMapping(value = "/{ownerId}", produces = "application/json")
	public ResponseEntity<Void> deleteOwner(@PathVariable("ownerId") int ownerId) {
		Owner owner = this.ownerService.findOwnerById(ownerId);
		if (owner == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		this.ownerService.deleteOwner(owner);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}

package com.petclinic.sfg.controller;

import com.petclinic.sfg.model.Visit;
import com.petclinic.sfg.service.VisitService;
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
@RequestMapping("api/visits")
public class VisitController {

	@Autowired
	private VisitService visitService;

	@GetMapping(value = "", produces = "application/json")
	public ResponseEntity<Collection<Visit>> getAllVisits(){
		Collection<Visit> visits = new ArrayList<Visit>();
		visits.addAll(this.visitService.findAllVisits());
		if (visits.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(visits, HttpStatus.OK);
	}

	@GetMapping(value = "/{visitId}", produces = "application/json")
	public ResponseEntity<Visit> getVisit(@PathVariable("visitId") int visitId){
		Visit visit = this.visitService.findVisitById(visitId);
		if(visit == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(visit, HttpStatus.OK);
	}

	@PostMapping(value = "", produces = "application/json")
	public ResponseEntity<Visit> addVisit(@RequestBody @Valid Visit visit,
										  BindingResult bindingResult, UriComponentsBuilder ucBuilder){
		HttpHeaders headers = new HttpHeaders();
		if(bindingResult.hasErrors() || (visit == null) || (visit.getPet() == null)){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);     //TO BE EXPANDED
		}
		this.visitService.saveVisit(visit);
		headers.setLocation(ucBuilder.path("/api/visits/{id}").buildAndExpand(visit.getId()).toUri());
		return new ResponseEntity<>(visit, headers, HttpStatus.CREATED);
	}

	@PutMapping(value = "/{visitId}", produces = "application/json")
	public ResponseEntity<Visit> updateVisit(@PathVariable("visitId") int visitId,
											 @RequestBody @Valid Visit visit, BindingResult bindingResult){
		if(bindingResult.hasErrors() || (visit == null) || (visit.getPet() == null)){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);     //TO BE EXPANDED
		}
		Visit currentVisit = this.visitService.findVisitById(visitId);
		if(currentVisit == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		currentVisit.setDate(visit.getDate());
		currentVisit.setDescription(visit.getDescription());
		currentVisit.setPet(visit.getPet());
		this.visitService.saveVisit(currentVisit);
		return new ResponseEntity<>(currentVisit, HttpStatus.NO_CONTENT);
	}

	@DeleteMapping(value = "/{visitId}", produces = "application/json")
	public ResponseEntity<Void> deleteVisit(@PathVariable("visitId") int visitId){
		Visit visit = this.visitService.findVisitById(visitId);
		if(visit == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		this.visitService.deleteVisit(visit);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}

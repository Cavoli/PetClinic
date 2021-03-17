package com.petclinic.sfg.service;

import com.petclinic.sfg.model.Visit;

import java.util.Collection;

public interface VisitService {

    Visit findVisitById(int visitId);

    Collection<Visit> findAllVisits();

    void saveVisit(Visit visit);

    void deleteVisit(Visit visit);

}

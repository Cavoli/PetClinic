package com.petclinic.sfg.service.impl;

import com.petclinic.sfg.model.Visit;
import com.petclinic.sfg.repository.VisitRepository;
import com.petclinic.sfg.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class VisitServiceImpl implements VisitService {

    private final VisitRepository visitRepository;

    @Autowired
    public VisitServiceImpl(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Override
    public Visit findVisitById(int visitId) {
        return visitRepository.findById(visitId).orElse(null);
    }

    @Override
    public Collection<Visit> findAllVisits() {
        return visitRepository.findAll();
    }

    @Override
    public void deleteVisit(Visit visit) {
        visitRepository.delete(visit);
    }

    @Override
    public void saveVisit(Visit visit) {
        visitRepository.save(visit);

    }
}

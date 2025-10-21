package com.petcode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.petcode.entity.Professor;
import com.petcode.repository.ProfessorRepository;

@RestController
public class ProfessorController {
    private final ProfessorRepository repository;
    
    @Autowired
    public ProfessorController(ProfessorRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/insert-prof")
    public void insertProfessor(@RequestParam("registrationNumber") String registrationNumber) {
        if(registrationNumber != null) {    
            Professor professor = new Professor(registrationNumber);
            repository.save(professor);
        }
    }

    @GetMapping("/count-prof")
    public Long countProfessors() {
        return repository.count();
    }
}

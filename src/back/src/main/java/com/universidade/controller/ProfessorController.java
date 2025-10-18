package com.universidade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.universidade.entity.Professor;
import com.universidade.repository.ProfessorRepository;

@RestController
public class ProfessorController {
    private final ProfessorRepository repository;
    
    @Autowired
    public ProfessorController(ProfessorRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/insert-prof")
    public void insertProfessor(@RequestParam("matricula") String matricula) {
        if(matricula != null) {    
            Professor professor = new Professor(matricula);
            repository.save(professor);
        }
    }

    @GetMapping("/count-prof")
    public Long countProfessores() {
        return repository.count();
    }
}

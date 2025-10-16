package com.universidade.entity;

import jakarta.persistence.*;

@Entity
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false, length = 20)
    private String matricula;

    /* Construtor para a JPA */
    protected Professor() {}

    /* Construtor com parâmetros */
    public Professor(String matricula) {
        this.matricula = matricula;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
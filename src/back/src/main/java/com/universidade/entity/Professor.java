package com.universidade.entity;

import jakarta.persistence.*;

/*
 * Não adicionei id do(s) departamento(s) que o professor pertence. 
 * Talvez deixar como se ele pertencesse a somente um departamento nao faça diferença.
 */

@Entity
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false, length = 20)
    private String matricula;

    @Column(name = "id_departamento")
    private Long idDepartamento;

    /* Construtor para a JPA */
    protected Professor() {
    }

    /* Construtor com parâmetros */
    public Professor(String matricula) {
        this.matricula = matricula;
    }

    public Long getId() {
        return id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Long getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Long idDepartamento) {
        this.idDepartamento = idDepartamento;
    }
}
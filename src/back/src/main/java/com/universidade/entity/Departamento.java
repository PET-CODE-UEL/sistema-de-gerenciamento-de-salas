package com.universidade.entity;

import jakarta.persistence.*;

@Entity
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @OneToOne
    @JoinColumn(name = "chefe_id")
    private Professor chefe;

    /* Construtor para a JPA */
    protected Departamento() {}

    public Departamento(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Professor getChefe() {
        return chefe;
    }

    public void setChefe(Professor chefe) {
        this.chefe = chefe;
    }
} 

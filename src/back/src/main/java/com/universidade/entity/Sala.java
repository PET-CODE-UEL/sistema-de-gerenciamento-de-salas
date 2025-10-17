package main.java.com.universidade.entity;

import jakarta.persistence.*;

@Entity
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 20)
    private String numero;

    @Column
    private Integer andar = 1;

    @Column
    private Integer capacidade;

    @Enumerated(EnumType.STRING)
    @Column
    private TipoSala tipo = TipoSala.AULA;

    @Column(name = "tem_projetor")
    private Boolean temProjetor = false;

    @Column(name = "tem_ar_condicionado")
    private Boolean temArCondicionado = false;

    @Column(name = "tem_computadores")
    private Boolean temComputadores = false;

    @Enumerated(EnumType.STRING)
    @Column
    private StatusSala status = StatusSala.ATIVA;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    public enum TipoSala {
        AULA, LABORATORIO, AUDITORIO, SALA_REUNIAO, ESCRITORIO
    }

    public enum StatusSala {
        ATIVA, MANUTENCAO, INATIVA
    }

    /* Construtor para a JPA */
    protected Sala() {}

    /* talvez seja melhor passar por parametro o 
        tem projetor, pc, ar (menos trabalho).
    */
    public Sala(String numero) {
        this.numero = numero;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Integer getAndar() {
        return andar;
    }

    public void setAndar(Integer andar) {
        this.andar = andar;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public TipoSala getTipo() {
        return tipo;
    }

    public void setTipo(TipoSala tipo) {
        this.tipo = tipo;
    }

    public Boolean getTemProjetor() {
        return temProjetor;
    }

    public void setTemProjetor(Boolean temProjetor) {
        this.temProjetor = temProjetor;
    }

    public Boolean getTemArCondicionado() {
        return temArCondicionado;
    }

    public void setTemArCondicionado(Boolean temArCondicionado) {
        this.temArCondicionado = temArCondicionado;
    }

    public Boolean getTemComputadores() {
        return temComputadores;
    }

    public void setTemComputadores(Boolean temComputadores) {
        this.temComputadores = temComputadores;
    }

    public StatusSala getStatus() {
        return status;
    }

    public void setStatus(StatusSala status) {
        this.status = status;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
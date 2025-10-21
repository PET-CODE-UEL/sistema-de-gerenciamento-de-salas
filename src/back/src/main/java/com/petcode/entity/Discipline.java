package com.petcode.entity;

import jakarta.persistence.*;

@Entity
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false, length = 20)
    private String code;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(name = "workload_hours")
    private Integer workloadHours;

    @Column(name = "department_id")
    private Long departmentId;

    /* Constructor for JPA */
    protected Discipline() {}

    public Discipline(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWorkloadHours() {
        return workloadHours;
    }

    public void setWorkloadHours(Integer workloadHours) {
        this.workloadHours = workloadHours;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}


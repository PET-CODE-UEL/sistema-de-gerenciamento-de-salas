package com.petcode.entity;

import jakarta.persistence.*;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 20)
    private String number;

    @Column
    private Integer floor = 1;

    @Column
    private Integer capacity;

    @Enumerated(EnumType.STRING)
    @Column
    private RoomType type = RoomType.CLASSROOM;

    @Column(name = "has_projector")
    private Boolean hasProjector = false;

    @Column(name = "has_air_conditioning")
    private Boolean hasAirConditioning = false;

    @Column(name = "has_computers")
    private Boolean hasComputers = false;

    @Enumerated(EnumType.STRING)
    @Column
    private RoomStatus status = RoomStatus.ACTIVE;

    @Column(columnDefinition = "TEXT")
    private String notes;

    public enum RoomType {
        CLASSROOM, LABORATORY, AUDITORIUM, MEETING_ROOM, OFFICE
    }

    public enum RoomStatus {
        ACTIVE, MAINTENANCE, INACTIVE
    }

    /* Constructor for JPA */
    protected Room() {}

    public Room(String number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public Boolean getHasProjector() {
        return hasProjector;
    }

    public void setHasProjector(Boolean hasProjector) {
        this.hasProjector = hasProjector;
    }

    public Boolean getHasAirConditioning() {
        return hasAirConditioning;
    }

    public void setHasAirConditioning(Boolean hasAirConditioning) {
        this.hasAirConditioning = hasAirConditioning;
    }

    public Boolean getHasComputers() {
        return hasComputers;
    }

    public void setHasComputers(Boolean hasComputers) {
        this.hasComputers = hasComputers;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}


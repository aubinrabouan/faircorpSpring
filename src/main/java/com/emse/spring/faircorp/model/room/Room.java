package com.emse.spring.faircorp.model.room;

import com.emse.spring.faircorp.model.building.Building;
import com.emse.spring.faircorp.model.light.Light;

import javax.persistence.*;
import java.util.List;

@Entity
public class Room {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer floor;


    @ManyToOne(optional = false)
    private Building building;

    @OneToMany(mappedBy = "room")
    private List<Light> lights;

    public Room(){

    }

    public Room(String name, Integer floor){
        this.name   = name;
        this.floor  = floor;
    }

    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public Integer getFloor(){
        return this.floor;
    }
    public void setFloor(Integer floor){
        this.floor = floor;
    }
    public List<Light> getLights() {
        return lights;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

}
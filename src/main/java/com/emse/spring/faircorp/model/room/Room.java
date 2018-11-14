package com.emse.spring.faircorp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Room {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer floor;



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

}
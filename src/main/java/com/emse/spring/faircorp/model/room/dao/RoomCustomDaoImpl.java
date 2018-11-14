package com.emse.spring.faircorp.model;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class RoomCustomDaoImpl implements RoomCustomDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Room findByName(String name){
        return (Room) em.createQuery("select rm from Room rm where rm.name = :name ", Room.class).setParameter("name",name);
    }
}

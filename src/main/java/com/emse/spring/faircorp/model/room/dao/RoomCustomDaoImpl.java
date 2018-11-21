package com.emse.spring.faircorp.model.room.dao;

import com.emse.spring.faircorp.model.light.Light;
import com.emse.spring.faircorp.model.room.Room;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoomCustomDaoImpl implements RoomCustomDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Room findByName(String name){
        return (Room) em.createQuery("select rm from Room rm where rm.name = :name ", Room.class).setParameter("name",name);
    }

    @Override
    public List<Light> findLightsRoom(Long roomId){
        String jpql = "select lt from Lights lt where lt.room.getId() = :value";
        return em.createQuery(jpql, Light.class).setParameter("value", roomId).getResultList();
    }
}

package com.emse.spring.faircorp.model.building.dao;

import com.emse.spring.faircorp.model.building.Building;
import com.emse.spring.faircorp.model.light.Light;
import com.emse.spring.faircorp.model.room.Room;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BuildingDaoImpl implements BuildingDaoCustom{
    @PersistenceContext
    private EntityManager em;




    @Override
    public Building findByName(String name) {
        return (Building) em.createQuery("select rm from Building rm where rm.name = :name", Building.class).setParameter("name", name);
    }

    @Override
    public List<Light> finDAllLights(long id) {
        Building building = (Building) em.createQuery("select rm from Building rm where rm.id = :id", Building.class).setParameter("id", id);

        List<Room> rooms = building.getRooms();
        List<Light> lights = null;
        for (Room room : rooms) {
           lights.addAll(room.getLights());
        }

        return lights;
    }


}

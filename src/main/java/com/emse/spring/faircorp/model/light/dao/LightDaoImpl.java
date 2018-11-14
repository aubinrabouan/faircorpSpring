package com.emse.spring.faircorp.model;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;



@Repository
public class LightDaoImpl implements LightDaoCustom{
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Light> findOnLights(){
        String jpql = "select lt from Light lt where lt.status = :value";
        return em.createQuery(jpql, Light.class).setParameter("value", Status.ON).getResultList();
    }




}

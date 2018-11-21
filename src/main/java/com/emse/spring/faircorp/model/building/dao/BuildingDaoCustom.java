package com.emse.spring.faircorp.model.building.dao;

import com.emse.spring.faircorp.model.building.Building;
import com.emse.spring.faircorp.model.light.Light;

import java.util.List;

public interface BuildingDaoCustom {
    Building findByName(String name);
    List<Light> finDAllLights(long id);
}

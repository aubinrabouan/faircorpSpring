package com.emse.spring.faircorp.model.building.dao;

import com.emse.spring.faircorp.model.building.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingDao extends JpaRepository<Building, Long>,BuildingDaoCustom {
}

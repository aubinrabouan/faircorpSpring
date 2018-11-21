package com.emse.spring.faircorp.model.light.dao;

import com.emse.spring.faircorp.model.light.Light;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LightDao extends JpaRepository<Light, Long>, LightDaoCustom {
}

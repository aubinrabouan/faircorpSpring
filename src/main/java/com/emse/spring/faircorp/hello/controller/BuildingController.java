package com.emse.spring.faircorp.hello.controller;


import com.emse.spring.faircorp.model.building.Building;
import com.emse.spring.faircorp.model.building.BuildingDto;
import com.emse.spring.faircorp.model.building.dao.BuildingDao;
import com.emse.spring.faircorp.model.light.Light;
import com.emse.spring.faircorp.model.light.dao.LightDao;
import com.emse.spring.faircorp.model.room.Room;
import com.emse.spring.faircorp.model.room.dao.RoomDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@CrossOrigin
@RequestMapping("/api/building")
@Transactional
public class BuildingController {

    @Autowired
    private LightDao lightsDao;

    @Autowired
    private RoomDao roomDao;

    @Autowired
    private BuildingDao buildingDao;

    @GetMapping
    public List<BuildingDto> findAll() {
        return buildingDao.findAll().stream()
                .map(BuildingDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public BuildingDto findById(@PathVariable Long id) {
        return buildingDao.findById(id).map(building -> new BuildingDto(building)).orElse(null);
    }

    @PostMapping
    public BuildingDto create(@RequestBody BuildingDto dto){

        Building building = null;
        if (dto.getId() != null){
            building = buildingDao.findById(dto.getId()).orElse(null);
        }

        if (building == null){
            building = buildingDao.save(new Building(dto.getName()));
        }

        return  new BuildingDto(building);

    }


    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id){
        Building building = buildingDao.findById(id).orElseThrow(IllegalArgumentException::new);
        for (Room r : building.getRooms()){
            for (Light l : r.getLights()){
                lightsDao.delete(l);
            }
            roomDao.delete(r);
        }
        buildingDao.deleteById(id);
    }
}

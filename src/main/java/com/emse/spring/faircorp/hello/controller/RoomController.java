package com.emse.spring.faircorp.hello.controller;

import com.emse.spring.faircorp.model.Status;
import com.emse.spring.faircorp.model.light.Light;
import com.emse.spring.faircorp.model.light.dao.LightDao;
import com.emse.spring.faircorp.model.room.Room;
import com.emse.spring.faircorp.model.room.RoomDto;
import com.emse.spring.faircorp.model.room.dao.RoomDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rooms")
@Transactional
public class RoomController {


    @Autowired
    private LightDao lightDao;
    @Autowired
    private RoomDao roomDao;

    @GetMapping
    public List<RoomDto> findAll() {
        return roomDao.findAll().stream()
                .map(RoomDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public RoomDto findById(@PathVariable Long id){
        return roomDao.findById(id).map(room -> new RoomDto(room)).orElse(null);
    }

    @PutMapping(path = "/{id}/switchLight")
    public RoomDto switchStatus(@PathVariable Long id){
        Room room = roomDao.findById(id).orElseThrow(IllegalArgumentException::new);
        for (Light l : room.getLights()){
            l.setStatus(l.getStatus() == Status.ON ? Status.OFF: Status.ON);
        }
        return new RoomDto(room);

    }

    @PostMapping
    public RoomDto create(@RequestBody RoomDto dto){
        Room room = null;
        if (dto.getId() != null) {
            room = roomDao.findById(dto.getId()).orElse(null);
        }

        if (room == null) {
            room = roomDao.save(new Room(dto.getName(), dto.getFloor()));
        }

        return new RoomDto(room);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id){
        Room room = roomDao.findById(id).orElseThrow(IllegalArgumentException::new);
        for (Light l : room.getLights()){
            lightDao.delete(l);
        }
        roomDao.deleteById(id);}

}

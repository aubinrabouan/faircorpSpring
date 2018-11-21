package com.emse.spring.faircorp.model.room.dao;

import com.emse.spring.faircorp.model.light.Light;
import com.emse.spring.faircorp.model.room.Room;

import java.util.List;

public interface RoomCustomDao {
    Room findByName(String name);
    List<Light> findLightsRoom(Long roomId);
}

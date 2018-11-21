package com.emse.spring.faircorp.model.room.dao;

import com.emse.spring.faircorp.model.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomDao extends JpaRepository<Room, Long>, RoomCustomDao {
}

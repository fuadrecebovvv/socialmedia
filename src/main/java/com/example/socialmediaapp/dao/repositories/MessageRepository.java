package com.example.socialmediaapp.dao.repositories;

import com.example.socialmediaapp.dao.entities.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    @Query("select m from MessageEntity m where (m.sender.id = :senderId and m.receiver.id = :receiverId) " +
            "or (m.sender.id = :receiverId and m.receiver.id = :senderId) order by m.sentAt")
    List<MessageEntity> findMessageEntitiesByUserId(
            @Param("senderId") Long senderId, @Param("receiverId") Long receiverId);
}

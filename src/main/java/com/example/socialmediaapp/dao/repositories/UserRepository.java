package com.example.socialmediaapp.dao.repositories;

import com.example.socialmediaapp.dao.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("select u from UserEntity u where u.username = :username")
    UserEntity getUserEntityByUsername(@Param("username") String username);

    @Query("select u from UserEntity u where u.username like %:username%") // top 10
    List<UserEntity> searchUser(@Param("username") String username);
}

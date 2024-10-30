package com.example.socialmediaapp.dao.repositories;

import com.example.socialmediaapp.dao.entities.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<LikeEntity, Long> {
}

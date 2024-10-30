package com.example.socialmediaapp.dao.repositories;

import com.example.socialmediaapp.dao.entities.FollowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FollowRepository extends JpaRepository<FollowEntity, Long> {
//    @Query("select * from FollowEntity where following_id = :followingId")
    @Query("select f from FollowEntity f where f.following.id = :followingId")
    List<FollowEntity> UserFollowers(@Param("followingId") Long followingId);

    @Query("select f from FollowEntity f where f.follower.id = :followerId")
    List<FollowEntity> UserFollowing(@Param("followerId") Long followerId);

}

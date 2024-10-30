package com.example.socialmediaapp.dao.repositories;

import com.example.socialmediaapp.dao.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

    @Query("select p from PostEntity p where p.user.id in :followingId order by p.createdAt desc")
    List<PostEntity> recentPosts(@Param("followingId") List<Long> followingId);

    @Query("select p from PostEntity p where p.user.profileType = 'PUBLIC'")
    List<PostEntity> publicProfilePosts();
}

package com.example.socialmediaapp.dao.repositories;

import com.example.socialmediaapp.dao.entities.CommentEntity;
import com.example.socialmediaapp.model.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    @Query("select c from CommentEntity c where c.post.id = :postId")
    List<CommentEntity> commentsOfPost(@Param("postId") Long postId);

    @Query("select c from CommentEntity c where c.mainComment.id = :commentId")
    List<CommentDto> responsesOfComment(@Param("commentId") Long commentId);
}

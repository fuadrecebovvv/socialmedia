package com.example.socialmediaapp.dao.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
@Getter
@Setter
@ToString
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String postContent;
    private Integer likeCount;
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "post")
    private List<LikeEntity> likes = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<CommentEntity> comments;
    @ElementCollection
    private List<Long> likedUserIds;
}

package com.example.socialmediaapp.dao.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "comments")
@Getter
@Setter
@ToString
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private PostEntity post;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
    private String content;
    private LocalDate createdAt;
    @ManyToOne
    @JoinColumn(name = "mainComment_id")
    private CommentEntity mainComment;
    @OneToMany(mappedBy = "mainComment", cascade = CascadeType.ALL)
    private List<CommentEntity> responses = new ArrayList<>();
    @ElementCollection
    private List<Long> commentLikedUserIds;

}

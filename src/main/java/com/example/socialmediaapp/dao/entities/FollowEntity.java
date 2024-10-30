package com.example.socialmediaapp.dao.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "follows")
@Getter
@Setter
@ToString
public class FollowEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "follower_id", nullable = false)
    private UserEntity follower;
    @ManyToOne
    @JoinColumn(name = "following_id", nullable = false)
    private UserEntity following;
}





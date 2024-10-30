package com.example.socialmediaapp.dao.entities;

import com.example.socialmediaapp.enums.ProfileType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String email;
    private String passwd;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
    @ToString.Exclude
    private List<PostEntity> posts = new ArrayList<>();
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
    @ToString.Exclude
    private List<LikeEntity> likes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "following")
    @ToString.Exclude
    private List<FollowEntity> followers;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "follower")
    @ToString.Exclude
    private List<FollowEntity> followings;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @ToString.Exclude
    private List<CommentEntity> comments;
    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<MessageEntity> sentMessages = new ArrayList<>();
    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<MessageEntity> receivedMessages = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private ProfileType profileType;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<RoleEntity> roles = new ArrayList<>();
    @ElementCollection
    private List<String> otps = new LinkedList<>();
}

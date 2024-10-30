package com.example.socialmediaapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowDto {
    private Long id;
    private Long followerId;
    private Long followingId;
    private String followerUsername;
    private String followingUsername;
}

package com.example.socialmediaapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private Long id;
    private String postContent;
    private Integer likeCount;
    private LocalDateTime createdAt;
    private String username;
    private List<CommentDto> comments;
}

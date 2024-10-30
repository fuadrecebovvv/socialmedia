package com.example.socialmediaapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private Long id;
    private String username;
    private Long postId;
    private Long userId;
    private String content;
    private LocalDate createdAt;
    private List<CommentDto> responses;
    private List<Long> likedUserIds;
}

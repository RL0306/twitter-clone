package com.example.twitterclone.dto;

import com.example.twitterclone.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

@Data
public class FollowerDTO {
    private Long id;

    @JsonBackReference(value = "user_following")
    private UserDTO from;

    @JsonBackReference(value = "user_follower")
    private UserDTO to;
}

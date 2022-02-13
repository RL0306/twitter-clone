package com.example.twitterclone.dto;

import com.example.twitterclone.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class FollowerDTO {
    private UserDTO from;
    private UserDTO to;


}

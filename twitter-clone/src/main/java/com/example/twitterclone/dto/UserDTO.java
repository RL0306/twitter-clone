package com.example.twitterclone.dto;

import com.example.twitterclone.entity.RoleEntity;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private String username;
    private String password;
    private String email;
    private List<RoleEntity> roles;
}

package com.example.twitterclone.dto;

import com.example.twitterclone.entity.UserRole;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@Data
public class UserDTO {
    private String username;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    private List<IpDTO> ipEntityList;
//    private List<FollowerDTO> followerEntityList;
    private List<FollowerDTO> followingEntityList;

}

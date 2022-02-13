package com.example.twitterclone.dto;

import com.example.twitterclone.entity.UserRole;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDTO {
    private String username;

    //Should this noT be followingDTO though, bit confused
    private List<UserDTO> following;

    private List<UserDTO> follower;

    public UserDTO(String username) {
        this.username = username;
    }
}

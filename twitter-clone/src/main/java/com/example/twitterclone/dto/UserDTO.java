package com.example.twitterclone.dto;

import com.example.twitterclone.entity.UserRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    private Long id;

    private String username;

    private List<UserDTO> following;

    private List<UserDTO> follower;

    //we might need to remove this
    private List<TweetDTO> tweets;

    public UserDTO(String username) {
        this.username = username;
    }
}

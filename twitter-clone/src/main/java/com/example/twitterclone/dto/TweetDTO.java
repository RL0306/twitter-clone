package com.example.twitterclone.dto;

import com.example.twitterclone.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TweetDTO {
    private String description;
    private int retweets;
    private int favourites;
    private String username;
    private LocalDateTime sentAt;

    public TweetDTO(String description, int retweets, int favourites, LocalDateTime sentAt) {
        this.description = description;
        this.retweets = retweets;
        this.favourites = favourites;
        this.sentAt = sentAt;
    }
}


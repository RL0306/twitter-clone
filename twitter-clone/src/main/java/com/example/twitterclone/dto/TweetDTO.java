package com.example.twitterclone.dto;

import com.example.twitterclone.entity.TweetEntity;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TweetDTO {
    private String description;
    private int retweets;
    private int favourites;
    private LocalDateTime time;
    private List<TweetDTO> tweetComments;
    private Long parentTweet;
}

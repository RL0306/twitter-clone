package com.example.twitterclone.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class TweetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String description;
    private int retweets;
    private int favourites;
    private LocalDateTime time;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private UserEntity userEntity;

    @OneToMany(fetch = FetchType.LAZY)

    private List<TweetEntity> tweetComments;

    //references tweet, ie for comments, should refrence the main tweet it's commenting on
    private Long parentTweet;

    //reference the tweet it's tweeting to, this is so that we know what comment it is referring to and also access the parent of the tweet
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private TweetEntity tweetTo;

    //for actual new tweet
    public TweetEntity(String description, UserEntity userEntity) {
        this.description = description;
        this.retweets = 0;
        this.favourites = 0;
        this.time = LocalDateTime.now();
        this.userEntity = userEntity;
        this.tweetComments = new ArrayList<>();
        //if 0 user made parent tweet
        this.parentTweet = 0L;
    }

    //For comments
    public TweetEntity(String description, UserEntity userEntity, Long parentTweet, TweetEntity tweetTo) {
        this.description = description;
        this.retweets = 0;
        this.favourites = 0;
        this.time = LocalDateTime.now();
        this.userEntity = userEntity;
        this.tweetComments = new ArrayList<>();
        this.parentTweet = parentTweet;
        this.tweetTo = tweetTo;
    }


}

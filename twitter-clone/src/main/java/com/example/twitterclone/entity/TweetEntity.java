package com.example.twitterclone.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserEntity userEntity;

    private LocalDateTime sentAt;

    @ElementCollection
    private Map<Long, TweetAttributes> tweetAttributes;


    public TweetEntity(String description, UserEntity userEntity) {
        this.description = description;
        this.userEntity = userEntity;
        this.retweets = 0;
        this.favourites = 0;
        this.sentAt = LocalDateTime.now();
        this.tweetAttributes = new HashMap<>();
    }
}

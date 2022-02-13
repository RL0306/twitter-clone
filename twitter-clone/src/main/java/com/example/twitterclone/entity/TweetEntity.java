package com.example.twitterclone.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

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

    public TweetEntity(String description, UserEntity userEntity) {
        this.description = description;
        this.userEntity = userEntity;
        this.retweets = 0;
        this.favourites = 0;
    }
}

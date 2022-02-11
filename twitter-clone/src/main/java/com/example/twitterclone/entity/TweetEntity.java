package com.example.twitterclone.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    @JsonManagedReference
    private TweetEntity parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    private List<TweetEntity> comments;

    @ManyToOne(cascade = CascadeType.ALL)
    private UserEntity userEntity;

    public TweetEntity(String description, UserEntity userEntity){
        this.description = description;
        this.retweets = 0;
        this.favourites = 0;
        this.time = LocalDateTime.now();
        this.userEntity = userEntity;
    }

    public TweetEntity(String description, TweetEntity parent, UserEntity userEntity){
        this.description = description;
        this.retweets = 0;
        this.favourites = 0;
        this.time = LocalDateTime.now();
        this.parent = parent;
        this.userEntity = userEntity;
    }



}




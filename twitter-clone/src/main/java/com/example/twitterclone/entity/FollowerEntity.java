package com.example.twitterclone.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor
public class FollowerEntity {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserEntity following;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserEntity follower;


    public FollowerEntity(UserEntity following, UserEntity follower) {
        this.following = following;
        this.follower = follower;
    }
}

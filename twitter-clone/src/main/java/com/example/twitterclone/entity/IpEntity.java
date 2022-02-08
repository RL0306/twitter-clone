package com.example.twitterclone.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
//look into constructor shit
public class IpEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String ip;

    @ManyToOne
    private UserEntity userEntity;

    public IpEntity(String ip, UserEntity userEntity) {
        this.ip = ip;
        this.userEntity = userEntity;
    }
}

package com.example.twitterclone.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class IpEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String ip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private UserEntity userEntity;

    private String token;

    private boolean recognised;


    public IpEntity(String ip, UserEntity userEntity, boolean recognised) {
        this.ip = ip;
        this.userEntity = userEntity;
        this.token = UUID.randomUUID().toString();
        this.recognised = recognised;
    }
}
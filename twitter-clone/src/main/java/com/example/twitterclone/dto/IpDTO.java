package com.example.twitterclone.dto;

import com.example.twitterclone.entity.UserEntity;
import lombok.Data;

import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
public class IpDTO {
    private Long id;

    private String ip;

    private String token;

    private boolean recognised;

    private LocalDateTime issued;
}

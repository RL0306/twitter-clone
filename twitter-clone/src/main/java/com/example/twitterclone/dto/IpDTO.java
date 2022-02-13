package com.example.twitterclone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class IpDTO {
    private String ip;
    private boolean recognised;
    private LocalDateTime issued;
}

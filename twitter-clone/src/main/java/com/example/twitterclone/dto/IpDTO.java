package com.example.twitterclone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
public class IpDTO {
    private String ip;
    private boolean recognised;
    private LocalDateTime issued;
}

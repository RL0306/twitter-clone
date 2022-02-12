package com.example.twitterclone.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TweetListDTO {
    private List<TweetDTO> tweetDTOS;
}

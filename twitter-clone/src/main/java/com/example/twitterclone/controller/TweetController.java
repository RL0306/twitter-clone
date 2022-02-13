package com.example.twitterclone.controller;

import com.example.twitterclone.dto.TweetDTO;
import com.example.twitterclone.dto.TweetRequestDTO;
import com.example.twitterclone.service.TweetService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tweet")
@AllArgsConstructor
public class TweetController {
    private final TweetService tweetService;

    @PostMapping
    public ResponseEntity<TweetDTO> createTweet(@RequestBody TweetRequestDTO tweetRequestDTO){
        TweetDTO tweet = tweetService.createTweet(tweetRequestDTO);
        return ResponseEntity.ok(tweet);
    }
}


package com.example.twitterclone.controller;

import com.example.twitterclone.dto.TweetDTO;
import com.example.twitterclone.dto.TweetRequestDTO;
import com.example.twitterclone.service.FollowerService;
import com.example.twitterclone.service.TweetService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tweet")
@AllArgsConstructor
public class TweetController {
    private final TweetService tweetService;
    private final FollowerService followerService;

    @PostMapping
    public ResponseEntity<TweetDTO> createTweet(@RequestBody TweetRequestDTO tweetRequestDTO){
        TweetDTO tweet = tweetService.createTweet(tweetRequestDTO);
        return ResponseEntity.ok(tweet);
    }

    /**
     * Get all following tweets for current user
     */

    @GetMapping("/all")
    public ResponseEntity<List<TweetDTO>> getAllFollowingTweets(){
        List<TweetDTO> allTweets = tweetService.getAllTweets();
        return ResponseEntity.ok(allTweets);

    }
}


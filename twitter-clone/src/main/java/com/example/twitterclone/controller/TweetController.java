package com.example.twitterclone.controller;

import com.example.twitterclone.dto.TweetDTO;
import com.example.twitterclone.dto.TweetRequestDTO;
import com.example.twitterclone.entity.TweetAttributes;
import com.example.twitterclone.service.FollowerService;
import com.example.twitterclone.service.TweetService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tweet")
@AllArgsConstructor
public class TweetController {
    private final TweetService tweetService;

    public static void main(String[] args) {
        List<String> list = Arrays.asList("Hello", "World", "Yellow");

        String collect = list.stream().collect(Collectors.joining(","));
        System.out.println(collect);
    }

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

    @PatchMapping("/retweet/{id}")
    public ResponseEntity<TweetDTO> createRetweet(@PathVariable("id") Long id){
        TweetDTO tweetDTO = tweetService.handleTweetRetweet(id);
        return ResponseEntity.ok(tweetDTO);
    }

    //does this need to go over in favourite controller, related to tweets tho
    @PatchMapping("/favourite/{id}")
    public ResponseEntity<TweetDTO> createFavourite(@PathVariable("id") Long id){
        TweetDTO tweetDTO = tweetService.handleTweetFavourite(id);
        return ResponseEntity.ok(tweetDTO);
    }


}


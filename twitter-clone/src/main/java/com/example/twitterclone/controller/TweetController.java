package com.example.twitterclone.controller;

import com.example.twitterclone.dto.TweetDTO;
import com.example.twitterclone.dto.TweetRequestDTO;
import com.example.twitterclone.entity.TweetEntity;
import com.example.twitterclone.repository.TweetRepository;
import com.example.twitterclone.service.TweetService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tweets")
@AllArgsConstructor
@Slf4j
public class TweetController {

    private final TweetService tweetService;

    @PostMapping
    public ResponseEntity<TweetDTO> createTweet(@RequestBody TweetRequestDTO tweetRequestDTO){
        TweetDTO tweet = tweetService.createTweet(tweetRequestDTO);
        return ResponseEntity.ok(tweet);
    }

    @GetMapping
    public ResponseEntity<List<TweetDTO>> getAllTweets(){
        List<TweetDTO> tweetList = tweetService.getAllTweets();
        return ResponseEntity.ok(tweetList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TweetDTO> getTweet(@PathVariable ("id") Long id){
        TweetDTO tweet = tweetService.getTweet(id);
        return ResponseEntity.ok(tweet);
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<TweetEntity>> getTweetComments(@PathVariable ("id") Long id){
        List<TweetEntity> tweet = tweetService.getTweetComments(id);
        return ResponseEntity.ok(tweet);
    }

    @PostMapping("/{id}")
    public ResponseEntity<TweetDTO> createTweetComment(@RequestBody TweetRequestDTO tweetRequestDTO, @PathVariable("id") Long id){
        TweetDTO tweetDTO = tweetService.createTweetComment(tweetRequestDTO, id);
        return ResponseEntity.ok(tweetDTO);
    }
}

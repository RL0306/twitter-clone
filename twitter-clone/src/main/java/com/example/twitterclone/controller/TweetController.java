package com.example.twitterclone.controller;

import com.example.twitterclone.dto.TweetDTO;
import com.example.twitterclone.dto.TweetRequestDTO;
import com.example.twitterclone.entity.TweetEntity;
import com.example.twitterclone.repository.TweetRepository;
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

    @PostMapping
    public ResponseEntity<TweetDTO> createTweet(@RequestBody TweetRequestDTO tweetRequestDTO){
        TweetDTO tweet = tweetService.createTweet(tweetRequestDTO);
        return ResponseEntity.ok(tweet);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<TweetEntity>> getTweet(@PathVariable ("id") Long id){
        List<TweetEntity> tweets = tweetService.getTweet(id);
        return ResponseEntity.ok(tweets);
    }

    @PostMapping("/{id}")
    public ResponseEntity<TweetDTO> createTweetComment(@RequestBody TweetRequestDTO tweetRequestDTO, @PathVariable("id") Long id){
        TweetDTO tweetDTO = tweetService.createTweetComment(tweetRequestDTO, id);

        return ResponseEntity.ok(tweetDTO);
    }
}

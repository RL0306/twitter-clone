package com.example.twitterclone.service;

import com.example.twitterclone.dto.TweetDTO;
import com.example.twitterclone.dto.TweetRequestDTO;
import com.example.twitterclone.entity.TweetEntity;
import com.example.twitterclone.entity.UserEntity;
import com.example.twitterclone.repository.TweetRepository;
import com.example.twitterclone.repository.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TweetService {
    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;
    private final ObjectMapper mapper;

    public TweetDTO createTweet(TweetRequestDTO tweetRequestDTO){

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity currentUser = userRepository.findByUsername(username).orElseThrow(IllegalStateException::new);

        TweetEntity tweetEntity = new TweetEntity(tweetRequestDTO.getDescription(), currentUser);

        tweetRepository.save(tweetEntity);

        return mapper.convertValue(tweetEntity, TweetDTO.class);
    }

    public TweetDTO createTweetComment(TweetRequestDTO tweetRequestDTO, Long id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity currentUser = userRepository.findByUsername(username).orElseThrow(IllegalStateException::new);

        //get tweet they are commenting on
        TweetEntity currentTweet = tweetRepository.findById(id).orElseThrow(IllegalStateException::new);

        //make new tweet
        TweetEntity tweetEntity = new TweetEntity(tweetRequestDTO.getDescription(), currentTweet,currentUser);

        //update parents child
        currentTweet.getComments().add(tweetEntity);

        tweetRepository.save(tweetEntity);

        return mapper.convertValue(tweetEntity, TweetDTO.class);

    }

    public TweetDTO getTweet(Long id) {
        TweetEntity tweetEntity = tweetRepository.findById(id).orElseThrow(IllegalStateException::new);
        return mapper.convertValue(tweetEntity, TweetDTO.class);
    }

    public List<TweetEntity> getTweetComments(Long id) {
        TweetEntity tweetEntity = tweetRepository.findById(id).orElseThrow(IllegalStateException::new);
        List<TweetEntity> comments = tweetEntity.getComments();
        return comments;
    }

    public List<TweetDTO> getAllTweets() {
        List<TweetEntity> allTweets = tweetRepository.findAll();

        return mapper.convertValue(allTweets, new TypeReference<List<TweetDTO>>() {});
    }
}

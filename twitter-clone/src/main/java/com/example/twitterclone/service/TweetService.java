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
        //so fucking sketchy
        //did this because initial parentTweet value is null
        tweetEntity.setParentTweet(tweetEntity.getId());
        tweetRepository.save(tweetEntity);

        return mapper.convertValue(tweetEntity, TweetDTO.class);
    }

    public TweetDTO createTweetComment(TweetRequestDTO tweetRequestDTO, Long id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity currentUser = userRepository.findByUsername(username).orElseThrow(IllegalStateException::new);

        //get tweet they are commenting on
        TweetEntity currentTweet = tweetRepository.findById(id).orElseThrow(IllegalStateException::new);

        TweetEntity tweetEntity = new TweetEntity(tweetRequestDTO.getDescription(), currentUser, currentTweet.getParentTweet(), currentTweet);
        tweetRepository.save(tweetEntity);

        return mapper.convertValue(tweetEntity, TweetDTO.class);

    }

    public List<TweetEntity> getTweet(Long id) {
//        TweetEntity currentTweet = tweetRepository.findById(id).orElseThrow(IllegalStateException::new);
//        return mapper.convertValue(currentTweet, TweetDTO.class);
        List<TweetEntity> tweetEntityList = tweetRepository.getAllByParentTweet(id);
        return tweetEntityList;
    }
}

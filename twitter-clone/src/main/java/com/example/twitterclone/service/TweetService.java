package com.example.twitterclone.service;

import com.example.twitterclone.dto.TweetDTO;
import com.example.twitterclone.dto.TweetRequestDTO;
import com.example.twitterclone.entity.TweetEntity;
import com.example.twitterclone.entity.UserEntity;
import com.example.twitterclone.populator.TweetPopulator;
import com.example.twitterclone.repository.TweetRepository;
import com.example.twitterclone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequestMapping("/api/tweet")
@RequiredArgsConstructor
public class TweetService {

    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;
    private final TweetPopulator tweetPopulator;
    private final FollowerService followerService;

    public TweetDTO createTweet(TweetRequestDTO tweetRequest){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity currentUser = userRepository.findByUsername(username).orElseThrow(IllegalAccessError::new);

        TweetEntity tweetEntity = new TweetEntity(tweetRequest.getDescription(), currentUser);

        tweetRepository.save(tweetEntity);

        return tweetPopulator.createTweetToDTO(tweetEntity);
    }

    public List<TweetDTO> getAllTweets() {
        List<Long> listOfFollowingId = followerService.getListOfFollowingId();
        List<TweetEntity> tweetList = tweetRepository.getTweetList(listOfFollowingId);
        List<TweetDTO> followerTweetListDTO = tweetPopulator.createFollowerTweetListDTO(tweetList);

        return followerTweetListDTO;
    }
}

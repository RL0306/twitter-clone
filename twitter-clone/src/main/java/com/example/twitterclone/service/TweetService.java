package com.example.twitterclone.service;

import com.example.twitterclone.dto.TweetDTO;
import com.example.twitterclone.dto.TweetRequestDTO;
import com.example.twitterclone.entity.TweetAttributes;
import com.example.twitterclone.entity.TweetEntity;
import com.example.twitterclone.entity.UserEntity;
import com.example.twitterclone.populator.TweetPopulator;
import com.example.twitterclone.repository.TweetRepository;
import com.example.twitterclone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.twitterclone.entity.TweetAttributes.*;

@Service
@RequestMapping("/api/tweet")
@RequiredArgsConstructor
@Slf4j
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

    public TweetDTO handleTweetRetweet(Long id){

        //put these 2 in a global method or some shit???
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity currentUser = userRepository.findByUsername(username).orElseThrow(IllegalAccessError::new);



        TweetEntity tweetEntity = tweetRepository.findById(id).orElseThrow(IllegalAccessError::new);
        TweetAttributes tweetAttributes = tweetEntity.getTweetAttributes().get(currentUser.getId());

        if(tweetAttributes == null || tweetAttributes == NONE){
            tweetEntity.getTweetAttributes().put(currentUser.getId(), RETWEET);
        } else if(tweetAttributes.equals(RETWEET) || tweetAttributes.equals(FOLLOWING_RETWEET)){
            if(tweetAttributes.equals(RETWEET))tweetEntity.getTweetAttributes().replace(currentUser.getId(), NONE);
            if(tweetAttributes.equals(FOLLOWING_RETWEET))tweetEntity.getTweetAttributes().replace(currentUser.getId(), FOLLOWING);
            tweetEntity.setRetweets(tweetEntity.getRetweets() - 1);
            tweetRepository.save(tweetEntity);
            return tweetPopulator.createTweetToDTO(tweetEntity);
        } else if(tweetAttributes.equals(FOLLOWING)){
            tweetEntity.getTweetAttributes().put(currentUser.getId(), FOLLOWING_RETWEET);
        }

        tweetEntity.setRetweets(tweetEntity.getRetweets() + 1);
        tweetRepository.save(tweetEntity);
        return tweetPopulator.createTweetToDTO(tweetEntity);

    }

    public TweetDTO handleTweetFavourite(Long id){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity currentUser = userRepository.findByUsername(username).orElseThrow(IllegalAccessError::new);
        TweetEntity tweetEntity = tweetRepository.findById(id).orElseThrow(IllegalAccessError::new);
        TweetAttributes tweetAttributes = tweetEntity.getTweetAttributes().get(currentUser.getId());


        //doesn't have any
        if(tweetAttributes == null | tweetAttributes == NONE){
            tweetEntity.getTweetAttributes().put(currentUser.getId(), FOLLOWING);
        } else if(tweetAttributes.equals(FOLLOWING) || tweetAttributes.equals(FOLLOWING_RETWEET)){
            if(tweetAttributes.equals(FOLLOWING))tweetEntity.getTweetAttributes().replace(currentUser.getId(), NONE);
            if(tweetAttributes.equals(FOLLOWING_RETWEET))tweetEntity.getTweetAttributes().replace(currentUser.getId(), RETWEET);
            tweetEntity.setFavourites(tweetEntity.getFavourites() - 1);
            tweetRepository.save(tweetEntity);
            return tweetPopulator.createTweetToDTO(tweetEntity);
        } else if(tweetAttributes.equals(RETWEET)){
            tweetEntity.getTweetAttributes().put(currentUser.getId(), FOLLOWING_RETWEET);
        }

        tweetEntity.setFavourites(tweetEntity.getFavourites() + 1);
        tweetRepository.save(tweetEntity);
        return tweetPopulator.createTweetToDTO(tweetEntity);

    }





}

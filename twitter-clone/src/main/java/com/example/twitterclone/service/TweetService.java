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
import java.util.function.BiConsumer;
import java.util.function.Function;
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

    public TweetDTO handler(final Long id, final TweetAttributes attr, final TweetAttributes attr2, final BiConsumer<TweetEntity, Integer> entityModifier) {
        final String username = SecurityContextHolder.getContext().getAuthentication().getName();
        final UserEntity currentUser = userRepository.findByUsername(username).orElseThrow(IllegalAccessError::new);

        final TweetEntity tweetEntity = tweetRepository.findById(id).orElseThrow(IllegalAccessError::new);
        final TweetAttributes tweetAttributes = tweetEntity.getTweetAttributes().get(currentUser.getId());

        if(tweetAttributes == null || tweetAttributes == NONE){
            tweetEntity.getTweetAttributes().put(currentUser.getId(), attr);
        } else if(tweetAttributes.equals(attr) || tweetAttributes.equals(FAVOURITE_RETWEET)){

            if(tweetAttributes.equals(attr))tweetEntity.getTweetAttributes().replace(currentUser.getId(), NONE);
            if(tweetAttributes.equals(FAVOURITE_RETWEET))tweetEntity.getTweetAttributes().replace(currentUser.getId(), attr2);

            entityModifier.accept(tweetEntity, -1);
            tweetRepository.save(tweetEntity);

            return tweetPopulator.createTweetToDTO(tweetEntity);
        } else if(tweetAttributes.equals(attr2)){
            tweetEntity.getTweetAttributes().put(currentUser.getId(), FAVOURITE_RETWEET);
        }

        entityModifier.accept(tweetEntity, +1);
        tweetRepository.save(tweetEntity);

        return tweetPopulator.createTweetToDTO(tweetEntity);

    }


    public TweetDTO handleTweetFavourite(final Long id) {
        return handler(id, FAVOURITE, RETWEET, (entity, diff) -> entity.setFavourites(entity.getFavourites() + diff));
    }

    public TweetDTO handleTweetRetweet(final Long id) {
        return handler(id, RETWEET, FAVOURITE,(entity, diff) -> entity.setRetweets(entity.getRetweets() + diff));
    }










}

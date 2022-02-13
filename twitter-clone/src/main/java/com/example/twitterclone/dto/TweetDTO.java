package com.example.twitterclone.dto;

import com.example.twitterclone.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * When we return userDTO it returns a list of TweetDTO is this really needed?
 * Can we not just write a query when we need them or should they be included
 * How do you judge?
 *
 * {
 *     "username": "Test123",
 *     "following": [
 *         {
 *             "username": "Test123"
 *         }
 *     ],
 *     "follower": [
 *         {
 *             "username": "Testing123"
 *         }
 *     ],
 *     "tweets": [
 *         {
 *             "description": "first",
 *             "retweets": 0,
 *             "favourites": 0
 *         },
 *         {
 *             "description": "second",
 *             "retweets": 0,
 *             "favourites": 0
 *         }
 *     ]
 * }
 *
 * json is that so no need to store user entity right because that's what the object is displaying?
 *
 *
 *
 *

 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TweetDTO {
    private String description;
    private int retweets;
    private int favourites;
}

/**

 * Don't need because the userEntity will return all the tweets, so no need?
 * private UserEntity userEntity;
 */

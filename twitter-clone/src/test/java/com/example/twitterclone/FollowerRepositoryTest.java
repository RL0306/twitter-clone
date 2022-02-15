package com.example.twitterclone;

import com.example.twitterclone.repository.FollowerRepository;
import com.example.twitterclone.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class FollowerRepositoryTest {

    @Autowired
    FollowerRepository followerRepository;

    @Test
    public void getFollower(){
        System.out.println(followerRepository.findAll().size());
    }
}

package com.example.twitterclone.controller;

import com.example.twitterclone.dto.UserDTO;
import com.example.twitterclone.service.FollowerService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/follower")
public class FollowerController {
    private final FollowerService followerService;

    @PostMapping("/{id}")
    public ResponseEntity<UserDTO> createFollower(@PathVariable("id") Long id){
        UserDTO follower = followerService.createFollower(id);

        return ResponseEntity.ok(follower);
    }

}

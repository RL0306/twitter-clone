package com.example.twitterclone.controller;
import com.example.twitterclone.dto.UserDTO;
import com.example.twitterclone.service.FollowerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/follower")
@Slf4j
public class FollowerController {
    private final FollowerService followerService;

    @PostMapping("/{id}")
    public ResponseEntity<UserDTO> createFollower(@PathVariable("id") Long id){
        UserDTO follower = followerService.createFollower(id);
        return ResponseEntity.ok(follower);
    }



}

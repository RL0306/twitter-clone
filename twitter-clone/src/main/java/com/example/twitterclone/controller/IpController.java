package com.example.twitterclone.controller;

import com.example.twitterclone.entity.IpEntity;
import com.example.twitterclone.service.IpService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ip")
@AllArgsConstructor
@Slf4j
public class IpController {

    private final IpService ipService;

    @PatchMapping
    public ResponseEntity<IpEntity> authoriseIP(@RequestParam String token){
        log.info("called");
        IpEntity ipEntity = ipService.validateToken(token);
        return ResponseEntity.ok(ipEntity);
    }
}

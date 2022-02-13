package com.example.twitterclone.controller;

import com.example.twitterclone.dto.IpDTO;
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
    public ResponseEntity<IpDTO> authoriseIP(@RequestParam String token){
        IpDTO ipDTO = ipService.validateToken(token);
        return ResponseEntity.ok(ipDTO);
    }
}

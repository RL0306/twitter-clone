package com.example.twitterclone.controller;

import com.example.twitterclone.dto.RegistrationDTO;
import com.example.twitterclone.dto.UserDTO;
import com.example.twitterclone.entity.UserEntity;
import com.example.twitterclone.repository.UserRepository;
import com.example.twitterclone.service.RegistrationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
@AllArgsConstructor
public class RegisterController {

    private final RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<UserDTO> register(@RequestBody RegistrationDTO registrationDTO){
        UserDTO userDTO = registrationService.register(registrationDTO);
        return ResponseEntity.ok(userDTO);
    }

}

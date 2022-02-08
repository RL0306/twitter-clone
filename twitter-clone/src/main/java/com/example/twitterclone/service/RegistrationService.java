package com.example.twitterclone.service;

import com.example.twitterclone.dto.RegistrationDTO;
import com.example.twitterclone.dto.UserDTO;
import com.example.twitterclone.entity.UserEntity;
import com.example.twitterclone.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final UserRepository userRepository;
    private final ObjectMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public UserDTO register(RegistrationDTO registrationDTO){
        //validation

        UserEntity userEntity = new
                UserEntity(registrationDTO.getUsername(), registrationDTO.getEmail(), passwordEncoder.encode(registrationDTO.getPassword()));

        userRepository.save(userEntity);

        return mapper.convertValue(userEntity, UserDTO.class);

    }
}

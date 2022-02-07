package com.example.twitterclone.service;

import com.example.twitterclone.dto.RegistrationDTO;
import com.example.twitterclone.dto.UserDTO;
import com.example.twitterclone.entity.RoleEntity;
import com.example.twitterclone.entity.UserEntity;
import com.example.twitterclone.repository.RoleRepository;
import com.example.twitterclone.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ObjectMapper mapper;

    public UserDTO register(RegistrationDTO registrationDTO){
        //validation shit
        //really bad will fix
        Optional<RoleEntity> ROLE_USER = roleRepository.findByName("ROLE_USER");


        UserEntity userEntity = new
                UserEntity(registrationDTO.getUsername(), registrationDTO.getEmail(), registrationDTO.getPassword(), new ArrayList<>());

        userRepository.save(userEntity);

        return mapper.convertValue(userEntity, UserDTO.class);

    }
}

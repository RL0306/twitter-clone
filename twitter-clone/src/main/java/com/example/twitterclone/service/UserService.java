package com.example.twitterclone.service;

import com.example.twitterclone.dto.UserDTO;
import com.example.twitterclone.entity.UserEntity;
import com.example.twitterclone.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ObjectMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow();

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(userEntity.getRole().name()));

        return new User(userEntity.getUsername(), userEntity.getPassword(), authorities);
    }

    public UserDTO getUser(Long id){
        UserEntity userEntity = userRepository.findById(id).orElseThrow(IllegalStateException::new);
        return mapper.convertValue(userEntity, UserDTO.class);
    }

}

package com.example.twitterclone.service;

import com.example.twitterclone.dto.LoginRequestDTO;
import com.example.twitterclone.entity.IpEntity;
import com.example.twitterclone.entity.UserEntity;
import com.example.twitterclone.repository.IpRepository;
import com.example.twitterclone.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class IpService {

    private final UserRepository userRepository;
    private final IpRepository ipRepository;
    private final EmailSendingService emailSendingService;

    public boolean createIP(String ipAddress, String username) throws IOException {
        //get logging in user
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(IllegalAccessError::new);

        //get all users ips
        List<IpEntity> ipList = ipRepository.getIpEntityByUserEntity(userEntity);

        //first time access
        if(ipList.size() == 0){
            IpEntity ipEntity = new IpEntity(ipAddress, userEntity, true);
            ipRepository.save(ipEntity);
            return true;
        }

        //feel like it's so messy
        Optional<IpEntity> ipEntityByIp = ipRepository.getIpEntityByIp(ipAddress);
        if(ipEntityByIp.isPresent()){
            return ipEntityByIp.get().isRecognised();
        } else{
            ipRepository.save(new IpEntity(ipAddress, userEntity, false));
        }

        return false;
    }

    //potential problems 1. time limit on token might be needed
    //when confirming the token user won't be logged and idk if that's a potential issue
    public IpEntity validateToken(String token){
        //get ip from token
        IpEntity ipEntity = ipRepository.getIpEntityByToken(token).orElseThrow(IllegalStateException::new);

        //update the ipEntity to status true (so now recognised ip)
        ipEntity.setRecognised(true);

        ipRepository.save(ipEntity);


        return ipEntity;

    }
}

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
            IpEntity ipEntity = new IpEntity(ipAddress, userEntity);
            ipRepository.save(ipEntity);
            return true;
        }

        boolean check = ipList.stream().anyMatch(ipEntity -> ipEntity.getIp().equals(ipAddress));

        if(!check){
            log.info("Ip doesn't exist for {} {}", username, ipAddress );
            emailSendingService.sendEmail("lakhr034@gmail.com",
                    "Confirm your new IP Address",
                    "You have logged in from a new IP : " + ipAddress + " please confirm if you recognise");
            return false;
        }



        return true;



    }
}

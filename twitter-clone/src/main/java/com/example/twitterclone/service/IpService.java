package com.example.twitterclone.service;

import com.example.twitterclone.dto.IpDTO;
import com.example.twitterclone.entity.IpEntity;
import com.example.twitterclone.entity.UserEntity;
import com.example.twitterclone.populator.IpPopulator;
import com.example.twitterclone.repository.IpRepository;
import com.example.twitterclone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class IpService {

    private final UserRepository userRepository;
    private final IpRepository ipRepository;
    private final IpPopulator ipPopulator;

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


        Optional<IpEntity> ipEntityByIp = ipRepository.getIpEntityByIp(ipAddress);
        if(ipEntityByIp.isPresent()){
            return ipEntityByIp.get().isRecognised();
        } else{
            ipRepository.save(new IpEntity(ipAddress, userEntity, false));
        }

        return false;
    }

    public IpDTO validateToken(String token){

        IpEntity ipEntity = ipRepository.getIpEntityByToken(token).orElseThrow(IllegalStateException::new);
        ipEntity.setRecognised(true);

        ipRepository.save(ipEntity);


        return ipPopulator.createIpDTO(ipEntity);

    }
}

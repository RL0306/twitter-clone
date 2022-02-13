package com.example.twitterclone.populator;

import com.example.twitterclone.dto.IpDTO;
import com.example.twitterclone.dto.UserDTO;
import com.example.twitterclone.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserPopulator {
    public UserDTO populate(UserEntity userEntity){
        final UserDTO userDTO = new UserDTO();

        userDTO.setUsername(userEntity.getUsername());
        userDTO.setRole(userEntity.getRole());

        //Just testing on ips but never gonna send dto with that
        List<IpDTO> ipDTO = userEntity.getIpEntityList().stream().map(e -> new IpDTO(e.getIp(), e.isRecognised(), e.getIssued())).collect(Collectors.toList());

        userDTO.setIpDTO(ipDTO);

        return userDTO;
    }
}

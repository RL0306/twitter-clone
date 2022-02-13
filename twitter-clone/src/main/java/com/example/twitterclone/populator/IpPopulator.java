package com.example.twitterclone.populator;

import com.example.twitterclone.dto.IpDTO;
import com.example.twitterclone.entity.IpEntity;
import org.springframework.stereotype.Component;

@Component
public class IpPopulator {

    public IpDTO createIpDTO(IpEntity ipEntity){
        IpDTO ipDTO = new IpDTO();
        ipDTO.setRecognised(ipEntity.isRecognised());
        return ipDTO;
    }
}

package com.fuel.ead.service;

import com.fuel.ead.model.ShedMaster;
import com.fuel.ead.repository.ShedMasterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ShedMasterService {

    private ShedMasterRepository shedMasterRepository;

    public ShedMaster register(ShedMaster shedMaster){
        return shedMasterRepository.save(shedMaster);
    }

    public ShedMaster login(ShedMaster shedMaster){
        ShedMaster reponsed = shedMasterRepository.findByEmail(shedMaster.getEmail());
        if(PasswordHashing.decrypt(reponsed.getPassword()).equals(shedMaster.getPassword())){
            return reponsed;
        }else {
            throw new RuntimeException("bad credentials");
        }

    }

    public ShedMaster findById(String id){
        return shedMasterRepository.findById(id).orElseThrow(()->new RuntimeException("shed master doesn't exist"));
    }




}

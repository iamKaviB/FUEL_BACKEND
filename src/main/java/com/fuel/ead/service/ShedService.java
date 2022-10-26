package com.fuel.ead.service;

import com.fuel.ead.model.Shed;
import com.fuel.ead.repository.ShedRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ShedService {

    private ShedRepository shedRepository;

    public Shed create(Shed shed){
        return shedRepository.save(shed);
    }

    public Shed findById(String shed){
        return shedRepository.findById(shed).orElseThrow(()->new RuntimeException("shed doesn't exist"));
    }

    public List<Shed> findAll(){
        return shedRepository.findAll();
    }

    public List<Shed> findAllByShedMaster(String shedMaster){
        return shedRepository.findAllByShedMaster_Id(shedMaster);
    }

}

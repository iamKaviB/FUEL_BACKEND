package com.fuel.ead.service;

import com.fuel.ead.model.Tank;
import com.fuel.ead.repository.TankRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TankService {

    private TankRepository tankRepository;

    public Tank create(Tank tank){
        return tankRepository.save(tank);
    }

    public Tank findById(String tank){
        return tankRepository.findById(tank).orElseThrow(()->new RuntimeException("tank doesnt exist"));
    }

    public List<Tank> findAll(){
        return tankRepository.findAll();
    }

    public List<Tank> findAllByShedId(String shedId){
        return tankRepository.findAllByShed_ShedId(shedId);
    }
}

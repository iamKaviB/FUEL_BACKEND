package com.fuel.ead.service;

import com.fuel.ead.model.Queue;
import com.fuel.ead.repository.QueueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QueueService {

    private QueueRepository queueRepository;

    public Queue create(Queue queue){
        return queueRepository.save(queue);
    }

    public Queue findById(String id){
        return queueRepository.findById(id).orElseThrow(()->new RuntimeException("Queue doesnt exist"));
    }

    public List<Queue> findAll(){
        return queueRepository.findAll();
    }

    public List<Queue> findAllByTank(String tank,String status){
        return queueRepository.findAllByTank_TankIdAndStatus(tank,status);
    }

    public List<Queue> findTime(String tank,String status){
        return queueRepository.findAllByTank_TankIdAndStatusOrderByIdDesc(tank,status);
    }

}

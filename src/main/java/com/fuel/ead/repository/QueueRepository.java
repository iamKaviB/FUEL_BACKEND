package com.fuel.ead.repository;

import com.fuel.ead.model.Queue;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface QueueRepository extends MongoRepository<Queue,String> {

    List<Queue> findAllByTank_TankIdAndStatus(String tank,String status);

    List<Queue> findAllByTank_TankIdAndStatusOrderByIdDesc(String id,String status);
}

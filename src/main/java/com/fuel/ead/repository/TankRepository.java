package com.fuel.ead.repository;

import com.fuel.ead.model.Tank;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TankRepository extends MongoRepository<Tank,String> {

    List<Tank> findAllByShed_ShedId(String shedId);

}

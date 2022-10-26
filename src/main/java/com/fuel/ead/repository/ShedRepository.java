package com.fuel.ead.repository;

import com.fuel.ead.model.Shed;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ShedRepository extends MongoRepository<Shed,String> {

    List<Shed> findAllByShedMaster_Id(String id);
}

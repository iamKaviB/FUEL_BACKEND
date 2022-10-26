package com.fuel.ead.repository;

import com.fuel.ead.model.ShedMaster;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShedMasterRepository extends MongoRepository<ShedMaster,String> {

    ShedMaster findByEmail(String email);
}

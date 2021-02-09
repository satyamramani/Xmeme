package com.satyam.xmeme.repository;

import com.satyam.xmeme.model.MemeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemeRepository extends MongoRepository<MemeEntity,String> {

}

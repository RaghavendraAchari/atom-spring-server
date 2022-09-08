package com.raghav.atom.repo;

import com.raghav.atom.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.ExecutableFindOperation;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UserRepo extends MongoRepository<User, ObjectId> {
    @Query("{'userName': ?0}")
    Optional<User> findByUserName(String userName);
}

package com.raghav.atom.repo;

import com.raghav.atom.model.Photo;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface PhotoRepo extends MongoRepository<Photo, ObjectId> {
    Page<Photo> findAll(Pageable pageable);
}

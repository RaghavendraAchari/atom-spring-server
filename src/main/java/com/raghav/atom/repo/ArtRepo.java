package com.raghav.atom.repo;

import com.raghav.atom.model.Art;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;


public interface ArtRepo extends MongoRepository<Art, ObjectId> {
}

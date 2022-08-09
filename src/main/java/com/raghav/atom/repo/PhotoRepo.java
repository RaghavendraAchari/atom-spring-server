package com.raghav.atom.repo;

import com.raghav.atom.model.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public interface PhotoRepo extends MongoRepository<Photo, String> {
}

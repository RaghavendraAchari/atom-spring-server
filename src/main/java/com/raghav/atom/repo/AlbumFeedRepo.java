package com.raghav.atom.repo;

import com.raghav.atom.model.AlbumFeed;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public interface AlbumFeedRepo extends MongoRepository<AlbumFeed, ObjectId> {
}

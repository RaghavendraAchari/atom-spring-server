package com.raghav.atom.repo;

import com.raghav.atom.model.AlbumFeed;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

public interface AlbumFeedRepo extends MongoRepository<AlbumFeed, ObjectId> {
    public Page<AlbumFeed> findAll(Pageable page);
}

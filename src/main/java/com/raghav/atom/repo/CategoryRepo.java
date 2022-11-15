package com.raghav.atom.repo;

import com.raghav.atom.model.Category;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepo extends MongoRepository<Category, ObjectId> {
}

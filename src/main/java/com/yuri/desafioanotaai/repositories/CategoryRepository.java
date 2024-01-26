package com.yuri.desafioanotaai.repositories;

import com.yuri.desafioanotaai.domain.category.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {}

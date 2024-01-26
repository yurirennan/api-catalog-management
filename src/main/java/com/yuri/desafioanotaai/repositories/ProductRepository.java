package com.yuri.desafioanotaai.repositories;

import com.yuri.desafioanotaai.domain.products.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {}

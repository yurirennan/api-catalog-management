package com.yuri.desafioanotaai.repositories.impl;

import com.yuri.desafioanotaai.domain.catalog.dto.CatalogCategory;
import com.yuri.desafioanotaai.domain.catalog.dto.CatalogDTO;
import com.yuri.desafioanotaai.domain.category.model.Category;
import com.yuri.desafioanotaai.domain.products.model.Product;
import com.yuri.desafioanotaai.repositories.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CatalogRepositoryImpl implements CatalogRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public CatalogRepositoryImpl(final MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<CatalogCategory> findCatalogByOwner(String owner) {
        LookupOperation lookupOperation = LookupOperation.newLookup()
                .from("products")
                .localField("_id")
                .foreignField("category._id")
                .as("itens");


        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("ownerId").is(owner)),
                lookupOperation
        );

        return mongoTemplate.aggregate(aggregation, Category.class, CatalogCategory.class).getMappedResults();
    }

}

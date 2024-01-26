package com.yuri.desafioanotaai.domain.products.model;

import com.yuri.desafioanotaai.domain.category.model.Category;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "products")
public class Product {

    @Id
    private String id;
    private String title;
    private String description;
    private double price;
    private Category category;
    private String ownerId;

}

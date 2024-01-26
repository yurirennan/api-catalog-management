package com.yuri.desafioanotaai.domain.products.dto.create.in;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.yuri.desafioanotaai.domain.category.model.Category;
import com.yuri.desafioanotaai.domain.products.model.Product;
import lombok.Data;

@Data
public class RequestCreateProductDTO {

    private String title;
    private String description;
    private double price;
    @JsonProperty("owner")
    private String ownerId;
    private String category;

    public Product toProduct() {
        final Product product = new Product();

        product.setTitle(this.getTitle());
        product.setDescription(this.getDescription());
        product.setPrice(this.getPrice());
        product.setOwnerId(this.getOwnerId());
        product.setCategory(Category.of(this.getCategory()));

        return product;
    }

}

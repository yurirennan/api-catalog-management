package com.yuri.desafioanotaai.domain.products.dto.update.in;

import com.yuri.desafioanotaai.domain.category.model.Category;
import com.yuri.desafioanotaai.domain.products.model.Product;
import lombok.Data;

@Data
public class ResquestUpdateProductDTO {

    private String title;
    private String description;
    private double price;
    private String category;

    public Product toProduct() {
        final Product product = new Product();

        product.setTitle(this.getTitle());
        product.setDescription(this.getDescription());
        product.setPrice(this.getPrice());
        product.setCategory(Category.of(this.getCategory()));

        return product;
    }
}

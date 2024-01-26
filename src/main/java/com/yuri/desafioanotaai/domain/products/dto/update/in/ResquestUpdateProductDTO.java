package com.yuri.desafioanotaai.domain.products.dto.update.in;

import com.yuri.desafioanotaai.domain.category.model.Category;
import com.yuri.desafioanotaai.domain.products.model.Product;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ResquestUpdateProductDTO {

    @NotBlank(message = "{errors.product.title.required}")
    private String title;
    @NotBlank(message = "{errors.product.description.required}")
    private String description;
    @NotBlank(message = "{errors.product.price.required}")
    private double price;
    @NotBlank(message = "{errors.product.category.required}")
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

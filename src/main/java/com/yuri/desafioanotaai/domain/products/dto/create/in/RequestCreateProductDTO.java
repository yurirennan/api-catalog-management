package com.yuri.desafioanotaai.domain.products.dto.create.in;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.yuri.desafioanotaai.domain.category.model.Category;
import com.yuri.desafioanotaai.domain.products.model.Product;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RequestCreateProductDTO {

    @NotBlank(message = "{errors.product.title.required}")
    private String title;
    @NotBlank(message = "{errors.product.description.required}")
    private String description;
    @NotBlank(message = "{errors.product.price.required}")
    private double price;
    @JsonProperty("owner")
    @NotBlank(message = "{errors.product.owner.required}")
    private String ownerId;
    @NotBlank(message = "{errors.product.category.required}")
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

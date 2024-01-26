package com.yuri.desafioanotaai.domain.products.dto.create.out;

import com.yuri.desafioanotaai.domain.category.model.Category;
import com.yuri.desafioanotaai.domain.products.dto.update.out.ResponseUpdateProductDTO;
import com.yuri.desafioanotaai.domain.products.model.Product;
import lombok.Data;

@Data
public class ResponseCreateProductDTO {

    private String id;
    private String title;
    private String description;
    private double price;
    private String category;
    private String owner;

    public static ResponseCreateProductDTO from(final Product product) {
        final ResponseCreateProductDTO responseCreateProductDTO = new ResponseCreateProductDTO();

        responseCreateProductDTO.setId(product.getId());
        responseCreateProductDTO.setTitle(product.getTitle());
        responseCreateProductDTO.setDescription(product.getDescription());
        responseCreateProductDTO.setPrice(product.getPrice());
        responseCreateProductDTO.setOwner(product.getOwnerId());
        responseCreateProductDTO.setCategory(product.getCategory().getId());

        return responseCreateProductDTO;
    }

}

package com.yuri.desafioanotaai.domain.products.dto.update.out;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.yuri.desafioanotaai.domain.products.model.Product;
import lombok.Data;

@Data
public class ResponseUpdateProductDTO {

    private String id;
    private String title;
    private String description;
    private double price;
    @JsonProperty("owner")
    private String ownerId;
    private String category;

    public static ResponseUpdateProductDTO from(final Product product) {
        final ResponseUpdateProductDTO responseUpdateProductDTO = new ResponseUpdateProductDTO();

        responseUpdateProductDTO.setId(product.getId());
        responseUpdateProductDTO.setTitle(product.getTitle());
        responseUpdateProductDTO.setDescription(product.getDescription());
        responseUpdateProductDTO.setPrice(product.getPrice());
        responseUpdateProductDTO.setOwnerId(product.getOwnerId());
        responseUpdateProductDTO.setCategory(product.getCategory().getId());

        return responseUpdateProductDTO;
    }

}

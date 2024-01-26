package com.yuri.desafioanotaai.domain.category.dto.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yuri.desafioanotaai.domain.category.model.Category;
import lombok.Data;

@Data
public class CategoryDTO {

    private String title;
    private String description;
    @JsonProperty("owner")
    private String ownerId;

    public Category toCategory() {
        Category category = new Category();

        category.setTitle(this.getTitle());
        category.setDescription(this.getDescription());
        category.setOwnerId(this.getOwnerId());

        return category;
    }

}

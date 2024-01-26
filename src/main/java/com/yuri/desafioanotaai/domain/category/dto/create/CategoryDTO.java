package com.yuri.desafioanotaai.domain.category.dto.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yuri.desafioanotaai.domain.category.model.Category;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryDTO {

    @NotBlank(message = "{errors.category.title.required}")
    private String title;
    @NotBlank(message = "{errors.category.description.required}")
    private String description;
    @JsonProperty("owner")
    @NotBlank(message = "{errors.category.owner.required}")
    private String ownerId;

    public Category toCategory() {
        Category category = new Category();

        category.setTitle(this.getTitle());
        category.setDescription(this.getDescription());
        category.setOwnerId(this.getOwnerId());

        return category;
    }

}

package com.yuri.desafioanotaai.domain.category.dto.update;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateCategoryDTO {

    @NotBlank(message = "{errors.category.title.required}")
    private String title;
    @NotBlank(message = "{errors.category.description.required}")
    private String description;

}

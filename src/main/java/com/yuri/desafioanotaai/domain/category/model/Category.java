package com.yuri.desafioanotaai.domain.category.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "categories")
public class Category {

    @Id
    private String id;
    private String title;
    private String description;
    private String ownerId;

    public static Category of(final String id) {
        final Category category = new Category();

        category.setId(id);

        return category;
    }

}

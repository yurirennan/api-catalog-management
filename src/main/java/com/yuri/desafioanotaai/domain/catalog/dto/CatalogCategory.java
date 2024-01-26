package com.yuri.desafioanotaai.domain.catalog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
public class CatalogCategory {

    @Field("title")
    @JsonProperty("category_item")
    private String categoryTitle;
    @Field("description")
    @JsonProperty("category_description")
    private String categoryDescription;
    @Field("itens")
    private List<CatalogItem> itens;

}

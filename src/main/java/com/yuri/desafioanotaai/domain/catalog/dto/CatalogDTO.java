package com.yuri.desafioanotaai.domain.catalog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CatalogDTO {

    private String owner;
    @JsonProperty("catalog")
    private List<CatalogCategory> categories;

}

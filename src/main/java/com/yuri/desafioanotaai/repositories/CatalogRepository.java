package com.yuri.desafioanotaai.repositories;

import com.yuri.desafioanotaai.domain.catalog.dto.CatalogCategory;
import com.yuri.desafioanotaai.domain.catalog.dto.CatalogDTO;

import java.util.List;

public interface CatalogRepository {

    List<CatalogCategory> findCatalogByOwner(final String owner);

}

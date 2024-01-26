package com.yuri.desafioanotaai.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuri.desafioanotaai.domain.catalog.dto.CatalogCategory;
import com.yuri.desafioanotaai.domain.catalog.dto.CatalogDTO;
import com.yuri.desafioanotaai.domain.catalog.exception.CatalogNotFoundException;
import com.yuri.desafioanotaai.domain.catalog.exception.CreateCatalogException;
import com.yuri.desafioanotaai.repositories.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class CatalogService {

    private final CatalogRepository catalogRepository;

    @Autowired
    public CatalogService(final CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    public void createCatalog(final String owner) {
        final List<CatalogCategory> categories = this.catalogRepository.findCatalogByOwner(owner);

        final CatalogDTO catalog = new CatalogDTO();
        catalog.setOwner(owner);
        catalog.setCategories(categories);

        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            final Path catalogsPath = Paths.get("./catalogs");
            final String absolutePath = catalogsPath.toAbsolutePath().toString();

            this.createDirectory(catalogsPath);
            objectMapper.writeValue(new File(String.format("%s/%s.json", absolutePath, owner)), catalog);
        } catch (IOException e) {
            throw new CreateCatalogException(owner);
        }

    }

    public CatalogDTO findCatalogByOwnerId(final String ownerId) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            final CatalogDTO catalogDTO = objectMapper.readValue(new File(String.format("./catalogs/%s.json", ownerId)), CatalogDTO.class);

            return catalogDTO;
        } catch (Exception e) {
            throw new CatalogNotFoundException(ownerId);
        }
    }

    private void createDirectory(final Path absolutePath) throws IOException {
        Files.createDirectories(absolutePath);
    }

}

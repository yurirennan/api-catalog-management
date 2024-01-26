package com.yuri.desafioanotaai.resources;

import com.yuri.desafioanotaai.domain.catalog.dto.CatalogDTO;
import com.yuri.desafioanotaai.services.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/catalogs")
public class CatalogController {

    private final CatalogService catalogService;

    @Autowired
    public CatalogController(final CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatalogDTO> getCatalog(@PathVariable("id") final String ownerId) {
        final CatalogDTO catalog = this.catalogService.findCatalogByOwnerId(ownerId);

        return ResponseEntity.ok(catalog);
    }

}

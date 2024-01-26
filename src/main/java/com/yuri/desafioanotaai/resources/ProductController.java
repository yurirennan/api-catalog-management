package com.yuri.desafioanotaai.resources;

import com.yuri.desafioanotaai.domain.products.dto.create.in.RequestCreateProductDTO;
import com.yuri.desafioanotaai.domain.products.dto.create.out.ResponseCreateProductDTO;
import com.yuri.desafioanotaai.domain.products.dto.update.in.ResquestUpdateProductDTO;
import com.yuri.desafioanotaai.domain.products.dto.update.out.ResponseUpdateProductDTO;
import com.yuri.desafioanotaai.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Void> saveProduct(@RequestBody @Valid final RequestCreateProductDTO requestCreateProductDTO) {
        this.productService.save(requestCreateProductDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<ResponseCreateProductDTO>> listAllProducts() {
        final List<ResponseCreateProductDTO> products = this.productService.listAllProducts();

        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseUpdateProductDTO> updateProduct(@PathVariable("id") final String productId,
                                                                  @RequestBody @Valid final ResquestUpdateProductDTO productDTO) {
        final ResponseUpdateProductDTO productUpdated = this.productService.update(productId, productDTO);

        return ResponseEntity.status(HttpStatus.OK).body(productUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") final String productId) {
        this.productService.delete(productId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}

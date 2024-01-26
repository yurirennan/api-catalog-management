package com.yuri.desafioanotaai.services;

import com.yuri.desafioanotaai.domain.category.exceptions.CategoryNotFoundException;
import com.yuri.desafioanotaai.domain.category.model.Category;
import com.yuri.desafioanotaai.domain.products.dto.create.in.RequestCreateProductDTO;
import com.yuri.desafioanotaai.domain.products.dto.create.out.ResponseCreateProductDTO;
import com.yuri.desafioanotaai.domain.products.dto.update.in.ResquestUpdateProductDTO;
import com.yuri.desafioanotaai.domain.products.dto.update.out.ResponseUpdateProductDTO;
import com.yuri.desafioanotaai.domain.products.exceptions.ProductNotFoundException;
import com.yuri.desafioanotaai.domain.products.model.Product;
import com.yuri.desafioanotaai.repositories.CategoryRepository;
import com.yuri.desafioanotaai.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final RabbitMQOwnerProducerService ownerProducerService;

    @Autowired
    public ProductService(final ProductRepository productRepository,
                          final CategoryRepository categoryRepository,
                          final RabbitMQOwnerProducerService ownerProducerService) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.ownerProducerService = ownerProducerService;
    }

    public void save(final RequestCreateProductDTO requestCreateProductDTO) {
        final Optional<Category> categoryOptional = this.categoryRepository.findById(requestCreateProductDTO.getCategory());

        if (categoryOptional.isEmpty()) {
            throw new CategoryNotFoundException(requestCreateProductDTO.getCategory());
        }

        final Product productToSave = requestCreateProductDTO.toProduct();

        this.productRepository.save(productToSave);

        this.ownerProducerService.emitCatalog(requestCreateProductDTO.getOwnerId());
    }

    public ResponseUpdateProductDTO update(final String productId, final ResquestUpdateProductDTO productDTO) {
        final Optional<Product> productOptional = this.productRepository.findById(productId);

        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException(productId);
        }

        final Product originalProduct = productOptional.get();

        final Product productToUpdate = productDTO.toProduct();
        productToUpdate.setId(originalProduct.getId());
        productToUpdate.setOwnerId(originalProduct.getOwnerId());

        this.productRepository.save(productToUpdate);

        this.ownerProducerService.emitCatalog(productToUpdate.getOwnerId());
        return ResponseUpdateProductDTO.from(productToUpdate);
    }

    public List<ResponseCreateProductDTO> listAllProducts() {
        final List<Product> allProducts = this.productRepository.findAll();

        return allProducts
                .stream()
                .map(ResponseCreateProductDTO::from)
                .toList();
    }

    public void delete(final String productId) {
        final Optional<Product> productOptional = this.productRepository.findById(productId);

        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException(productId);
        }

        this.productRepository.deleteById(productId);
    }

}

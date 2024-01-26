package com.yuri.desafioanotaai.services;

import com.yuri.desafioanotaai.domain.category.dto.create.CategoryDTO;
import com.yuri.desafioanotaai.domain.category.dto.update.UpdateCategoryDTO;
import com.yuri.desafioanotaai.domain.category.exceptions.CategoryNotFoundException;
import com.yuri.desafioanotaai.domain.category.model.Category;
import com.yuri.desafioanotaai.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final RabbitMQOwnerProducerService ownerProducerService;

    @Autowired
    public CategoryService(final CategoryRepository categoryRepository,
                           final RabbitMQOwnerProducerService ownerProducerService) {
        this.categoryRepository = categoryRepository;
        this.ownerProducerService = ownerProducerService;
    }

    public Category save(final CategoryDTO categoryDTO) {
        final Category category = categoryDTO.toCategory();

        this.ownerProducerService.emitCatalog(categoryDTO.getOwnerId());
        return this.categoryRepository.save(category);
    }

    public List<Category> listAllCategories() {
        return this.categoryRepository.findAll();
    }

    public Category updateCategory(final String categoryId, final UpdateCategoryDTO categoryDTO) {
        final Optional<Category> categoryOptional = this.categoryRepository.findById(categoryId);

        if(categoryOptional.isEmpty()) {
            throw new CategoryNotFoundException(categoryId);
        }

        final Category categoryToUpdate = categoryOptional.get();
        categoryToUpdate.setTitle(categoryDTO.getTitle());
        categoryToUpdate.setDescription(categoryDTO.getDescription());

        this.ownerProducerService.emitCatalog(categoryToUpdate.getOwnerId());
        return this.categoryRepository.save(categoryToUpdate);
    }

    public void deleteCategory(final String categoryId) {
        final Optional<Category> categoryOptional = this.categoryRepository.findById(categoryId);

        if(categoryOptional.isEmpty()) {
            throw new CategoryNotFoundException(categoryId);
        }

        this.categoryRepository.deleteById(categoryId);
    }

}

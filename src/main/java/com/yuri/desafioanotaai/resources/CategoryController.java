package com.yuri.desafioanotaai.resources;

import com.yuri.desafioanotaai.domain.category.dto.create.CategoryDTO;
import com.yuri.desafioanotaai.domain.category.dto.update.UpdateCategoryDTO;
import com.yuri.desafioanotaai.domain.category.model.Category;
import com.yuri.desafioanotaai.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(final CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @PostMapping
    public ResponseEntity<Category> saveCategory(@RequestBody @Valid CategoryDTO categoryDTO) {
        final Category category = this.categoryService.save(categoryDTO);

        return ResponseEntity.ok(category);
    }

    @GetMapping
    public ResponseEntity<List<Category>> listAllCategories() {
        final List<Category> categories = this.categoryService.listAllCategories();

        return ResponseEntity.ok(categories);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") final String categoryId,
                                                   @RequestBody @Valid final UpdateCategoryDTO categoryDTO) {
        final Category category = this.categoryService.updateCategory(categoryId, categoryDTO);

        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable("id") final String categoryId) {
        this.categoryService.deleteCategory(categoryId);

        return ResponseEntity.ok().build();
    }

}

package com.example.northwind.api.controllers;

import com.example.northwind.business.abstracts.ICategoryService;
import com.example.northwind.entities.concretes.Category;
import com.example.northwind.exceptions.DeletingErrorByRelationException;
import com.example.northwind.exceptions.NotFoundException;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class CategoriesController {

  @Autowired
  ICategoryService categoryService;

  @GetMapping("/categories/{id}")
  public Category getCategoryById(@PathVariable("id") int id) throws NotFoundException {
    return categoryService.findById(id);
  }

  @PostMapping("/categories")
  public Category save(@Valid @RequestBody Category category) {
    return categoryService.save(category);
  }

  @DeleteMapping("/categories/{id}")
  public void delete(@PathVariable(value = "id") int id)
      throws NotFoundException, DeletingErrorByRelationException {

    Category deletedCategory = categoryService.findById(id);
    categoryService.delete(deletedCategory);
  }

  @PutMapping("/categories/{id}")
  public Category update(@PathVariable(value = "id") int id,
      @Valid @RequestBody Category category) throws NotFoundException {

    category.setId(id);
    return categoryService.update(category);
  }

  @GetMapping("/categories")
  public List<Category> getAll() {
    return categoryService.getAll();
  }

  @GetMapping("/categoriesOrderByDesc/{columnName}") // getAll alternatifi
  public List<Category> getCategoriesOrderByDesc(
      @PathVariable(value = "columnName") String columnName) {
    return categoryService.getCategoriesOrderByDesc(columnName);
  }

  @GetMapping("/getCategoriesOrderByAsc/{columnName}")
  public List<Category> getCategoriesOrderByAsc(
      @PathVariable(value = "columnName") String columnName) {
    return categoryService.getCategoriesOrderByAsc(columnName);
  }
}

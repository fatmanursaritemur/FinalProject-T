package com.example.northwind.business.abstracts;

import com.example.northwind.entities.concretes.Category;
import com.example.northwind.exceptions.DeletingErrorByRelationException;
import com.example.northwind.exceptions.NotFoundException;
import java.util.List;

public interface ICategoryService {

  Category findById(int categoryId) throws NotFoundException;

  Category update(Category category) throws NotFoundException;

  Category save(Category category);

  List<Category> getCategoriesOrderByDesc(String columnName);

  List<Category> getCategoriesOrderByAsc(String columnName);

  List<Category> getAll();

  void delete(Category category) throws DeletingErrorByRelationException;


}

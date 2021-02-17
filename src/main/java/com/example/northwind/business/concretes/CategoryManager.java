package com.example.northwind.business.concretes;

import com.example.northwind.business.abstracts.ICategoryService;
import com.example.northwind.dataAccess.concretes.CategoryRepository;
import com.example.northwind.entities.concretes.Category;
import com.example.northwind.exceptions.DeletingErrorByRelationException;
import com.example.northwind.exceptions.NotFoundException;
import com.example.northwind.business.utilities.UpdateColumnUtil;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CategoryManager implements ICategoryService {

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  UpdateColumnUtil updateColumnUtil;

  @Override
  public List<Category> getAll() {
    return categoryRepository.findAll();
  }

  @Override
  public Category findById(int categoryId) throws NotFoundException {
    return categoryRepository.findById(categoryId)
        .orElseThrow(() -> new NotFoundException(Category.class.getSimpleName(), String.valueOf(categoryId)));
  }

  @Override
  public Category update(Category category) throws NotFoundException {
    Category target = categoryRepository.findById(category.getId())
        .orElseThrow(() -> new NotFoundException(Category.class.getSimpleName(), String.valueOf(category.getId())));
    BeanUtils.copyProperties(category, target, updateColumnUtil.getNullPropertyNames(category));
    return categoryRepository.save(target);
  }

  @Override
  public void delete(Category category) throws DeletingErrorByRelationException {
    try {
      categoryRepository.delete(category);
    } catch (DataIntegrityViolationException e) {
      throw new DeletingErrorByRelationException(Category.class.getSimpleName(), category.getId());
    }
  }

  @Override
  public Category save(Category category) {
    return categoryRepository.save(category);
  }

  @Override
  public List<Category> getCategoriesOrderByDesc(String columnName) {
    return categoryRepository.findAll(Sort.by(columnName).descending());
  }

  @Override
  public List<Category> getCategoriesOrderByAsc(String columnName) {
    return categoryRepository.findAll(Sort.by(columnName).ascending());
  }
}

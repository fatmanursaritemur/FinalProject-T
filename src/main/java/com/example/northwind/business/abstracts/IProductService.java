package com.example.northwind.business.abstracts;

import com.example.northwind.entities.concretes.Product;
import com.example.northwind.exceptions.CategoryOutOfBoundsException;
import com.example.northwind.exceptions.DeletingErrorByRelationException;
import com.example.northwind.exceptions.NotFoundException;
import java.util.List;

public interface IProductService {

  Product findById(int productId);

  Product update(Product product) throws NotFoundException;

  Product save(Product product) throws CategoryOutOfBoundsException;

  List<Product> getAll();

  Integer countProductByCategoryId(Product product);

  void delete(Product product) throws DeletingErrorByRelationException;

  boolean isCategoryFull(Product product);

}

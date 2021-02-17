package com.example.northwind.api.controllers;

import com.example.northwind.business.abstracts.IProductService;
import com.example.northwind.entities.concretes.Product;
import com.example.northwind.exceptions.CategoryOutOfBoundsException;
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
public class ProductsController {

  @Autowired
  IProductService productService;

  @GetMapping("/products")
  public List<Product> getAll() {
    return productService.getAll();
  }


  @GetMapping("/products/{id}")
  public Product getProductById(@PathVariable("id") int id) {
    return productService.findById(id);
  }

  @PostMapping("/products")
  public Product save(@Valid @RequestBody Product product) throws CategoryOutOfBoundsException {
    return productService.save(product);
  }

  @PutMapping("/products/{id}")
  public Product update(@PathVariable(value = "id") int id, @Valid @RequestBody Product product)
      throws NotFoundException {
    product.setId(id);
    return productService.update(product);
  }

  @DeleteMapping("/products/{id}")
  public void delete(@PathVariable(value = "id") int id)
      throws DeletingErrorByRelationException {

    Product deletedProduct = productService.findById(id);
    productService.delete(deletedProduct);
  }
}

package com.example.northwind.dataAccess.concretes;

import com.example.northwind.entities.concretes.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

  Integer countProductByCategoryId(int categoryId);
}

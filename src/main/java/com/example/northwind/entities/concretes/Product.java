package com.example.northwind.entities.concretes;

import com.example.northwind.entities.abstracts.IEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

@Data
@Entity
@DynamicUpdate
@Table (name = "products")
public class Product implements IEntity {

  @Id
  @SequenceGenerator (name = "yourSequenceGenerator", allocationSize = 80, initialValue = 1)
  @GeneratedValue (strategy = GenerationType.AUTO, generator = "yourSequenceGenerator")
  @Column (name = "product_id")
  private int id;

  @Size (min = 2, max = 200)
  @Column (name = "product_name")
  private String productName;

  @Column (name = "category_id")
  private int categoryId;

  @Column (name = "unit_price")
  private double unitPrice;

  @Column (name = "quantity_per_unit")
  private String quantityPerUnit;

  @Column (name = "discontinued")
  private int discontinued;
}

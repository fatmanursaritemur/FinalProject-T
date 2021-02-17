package com.example.northwind.entities.concretes;

import com.example.northwind.entities.abstracts.IEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "categories")
public class Category implements IEntity {

  @Id
  @SequenceGenerator(name = "yourSequenceGenerator", allocationSize = 80, initialValue = 1)
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "yourSequenceGenerator")
  @Column(name = "category_id")
  private int id;

  @Column(name = "category_name")
  private String categoryName;

  @Column(name = "description")
  private String description;

}

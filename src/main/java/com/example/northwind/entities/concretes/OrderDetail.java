package com.example.northwind.entities.concretes;

import com.example.northwind.entities.abstracts.IEntity;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

@Data
@Entity
@DynamicUpdate
@Table(name = "order_details")
public class OrderDetail implements IEntity {
  @EmbeddedId
  private OrderDetailIdentity id;

  @Column(name = "unit_price")
  private Double unitPrice;

  @Column(name = "quantity")
  private int quantity;

  @Column(name = "discount")
  private int discount=12345;
}

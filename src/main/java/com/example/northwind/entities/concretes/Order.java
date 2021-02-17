package com.example.northwind.entities.concretes;

import com.example.northwind.entities.abstracts.IEntity;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

@Data
@Entity
@DynamicUpdate
@Table(name = "orders")
public class Order implements IEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "order_id")
  private int id;

  @Column(name = "customer_id")
  private String customerId;

  @Column(name = "order_date")
  private Date orderDate = new Date();
}

package com.example.northwind.entities.concretes;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailIdentity implements Serializable {

  @Column(name = "order_id")
  private int orderId;

  @Column(name = "product_id")
  private int productId;
}

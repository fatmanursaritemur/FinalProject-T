package com.example.northwind.entities.concretes;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@DynamicUpdate
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardIdentity implements Serializable {

  @Column(name = "customer_id")
  private String customerId;

  @Max(32767) // smallint olarak tanımlanmıştır
  @Column(name = "product_id")
  private int productId;

}
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
@Table(name = "cards")
public class Card implements IEntity {

  @EmbeddedId
  private CardIdentity id;

  @Column(name = "quantity")
  private int quantity = 1;
}

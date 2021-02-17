package com.example.northwind.dataAccess.concretes;

import com.example.northwind.entities.concretes.Card;
import com.example.northwind.entities.concretes.CardIdentity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, CardIdentity> {

  List<Card> findAllByIdCustomerId(String customerId);

  void deleteCardsByIdCustomerId(String customerId);

  boolean existsCardByIdCustomerId(String customerId);

}

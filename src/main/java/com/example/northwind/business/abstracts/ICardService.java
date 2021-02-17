package com.example.northwind.business.abstracts;

import com.example.northwind.entities.concretes.Card;
import com.example.northwind.entities.concretes.CardIdentity;
import com.example.northwind.exceptions.NotExistByPropertyExeption;
import com.example.northwind.exceptions.NotFoundException;
import java.util.List;

public interface ICardService {

  Card update(Card card);

  Card save(Card card) throws NotFoundException;

  Card getCardById(CardIdentity cardIdentity) throws NotFoundException;

  List<Card> getCardsByCustomer(String customerId);

  boolean isExistsCardByIdCustomerId(String customerId);

  void deleteCardsByCustomer(String customerId) throws NotExistByPropertyExeption;

  void delete(Card card);

}

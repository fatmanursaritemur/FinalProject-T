package com.example.northwind.api.controllers;

import com.example.northwind.business.abstracts.ICardService;
import com.example.northwind.entities.concretes.Card;
import com.example.northwind.entities.concretes.CardIdentity;
import com.example.northwind.exceptions.NotExistByPropertyExeption;
import com.example.northwind.exceptions.NotFoundException;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class CardController {

  @Autowired
  ICardService cardService;

  @PostMapping("/cards")
  public Card save(@Valid @RequestBody Card card) throws NotFoundException {
    return cardService.save(card);
  }

  @GetMapping("/cardsbycustomer/{customerId}")
  public List<Card> getCardsByCustomer(
      @PathVariable(value = "customerId") String customerId) {

    return cardService.getCardsByCustomer(customerId);
  }

  @DeleteMapping("/cards/{customerId}/{productId}")
  public void delete(@PathVariable(value = "customerId") String customerId,
      @PathVariable(value = "productId") int productId) throws NotFoundException {

    Card deletedCard = cardService.getCardById(new CardIdentity(customerId, productId)); // düzelt
    cardService.delete(deletedCard);
  }

  @DeleteMapping("/cards/{customerId}")
  public void deleteCardsByCustomer(@PathVariable(value = "customerId") String customerId)
      throws NotExistByPropertyExeption {

    cardService.deleteCardsByCustomer(customerId);
  }

  @PutMapping("/cards/{customerId}/{productId}/{quantity}")
  public Card changeQuantity(@PathVariable(value = "quantity") int quantity,
      @PathVariable(value = "customerId") String customerId,
      @PathVariable(value = "productId") int productId) throws NotFoundException {

    Card updateCard = cardService.getCardById(new CardIdentity(customerId, productId));
    updateCard.setQuantity(quantity);
    return cardService.update(updateCard);
  }

}

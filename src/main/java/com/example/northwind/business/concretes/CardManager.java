package com.example.northwind.business.concretes;


import com.example.northwind.business.abstracts.ICardService;
import com.example.northwind.dataAccess.concretes.CardRepository;
import com.example.northwind.entities.concretes.Card;
import com.example.northwind.entities.concretes.CardIdentity;
import com.example.northwind.entities.concretes.Product;
import com.example.northwind.exceptions.NotExistByPropertyExeption;
import com.example.northwind.exceptions.NotFoundException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@Service
@Transactional
public class CardManager implements ICardService {

  @Autowired
  private CardRepository cardRepository;

  @Override
  public Card update(Card card) {
    return cardRepository.save(card);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  @Override
  public Card save(Card card) throws NotFoundException {
    if(cardRepository.existsById(card.getId())){
     card.setQuantity(getCardById(card.getId()).getQuantity()+card.getQuantity()); // entity default quantity =1
    }
    return cardRepository.save(card);
  }

  @Override
  public List<Card> getCardsByCustomer(String customerId) {
    return cardRepository.findAllByIdCustomerId(customerId);
  }

  @Override
  public Card getCardById(CardIdentity cardIdentity) throws NotFoundException {
    return cardRepository.findById(cardIdentity)
        .orElseThrow(() -> new NotFoundException(Card.class.getSimpleName(), cardIdentity.toString()));
  }

  @Override
  public boolean isExistsCardByIdCustomerId(String customerId) {
    return cardRepository.existsCardByIdCustomerId(customerId);
  }

  @Override
  public void deleteCardsByCustomer(String customerId) throws NotExistByPropertyExeption {
    if ( isExistsCardByIdCustomerId(customerId) ) {
      cardRepository.deleteCardsByIdCustomerId(customerId);
    }
    else {
      throw new NotExistByPropertyExeption(Product.class.getSimpleName(), customerId, "customerid");
    }

  }


  public void deleteCardsByCustomer3(String customerId) throws NotExistByPropertyExeption {
      if(!isExistsCardByIdCustomerId(customerId))
       throw new NotExistByPropertyExeption(Product.class.getSimpleName(), customerId, "customerid");
      cardRepository.deleteCardsByIdCustomerId(customerId);
   }

  @Override
  public void delete(Card card) {
     cardRepository.delete(card);
  }

}

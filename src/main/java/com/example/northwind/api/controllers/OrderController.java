package com.example.northwind.api.controllers;

import com.example.northwind.business.abstracts.IOrderService;
import com.example.northwind.entities.concretes.Order;
import com.example.northwind.exceptions.DeletingErrorByRelationException;
import com.example.northwind.exceptions.NotExistByPropertyExeption;
import com.example.northwind.exceptions.NotFoundException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class OrderController {

  @Autowired
  IOrderService orderService;

  @PostMapping("/orders")
  public Order save(@RequestBody Order order) {
    return orderService.save(order);
  }

  @PostMapping("/orders/{customer_id}")
  public void order(@PathVariable(value = "customer_id") String customerId)
      throws NotExistByPropertyExeption, NotFoundException {

    Order order = new Order();
    order.setCustomerId(customerId);
    orderService.order(order);
  }

  @PutMapping("/orders/{id}")
  public Order update(@PathVariable(value = "id") int id, @Valid @RequestBody Order order)
      throws NotFoundException {
    order.setId(id);
    return orderService.update(order);
  }

  @DeleteMapping("/orders/{id}")
  public void delete(@PathVariable(value = "id") int id)
      throws NotFoundException, DeletingErrorByRelationException {

    Order deletedOrder = orderService.findById(id);
    orderService.delete(deletedOrder);
  }
}

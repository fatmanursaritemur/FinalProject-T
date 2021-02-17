package com.example.northwind.api.controllers;

import com.example.northwind.business.abstracts.IOrderDetailService;
import com.example.northwind.entities.concretes.OrderDetail;
import com.example.northwind.entities.concretes.OrderDetailIdentity;
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
// gerekli mi???


@RestController
@RequestMapping("api/v1")
public class OrderDetailController {

  @Autowired
  IOrderDetailService orderDetailService;

  @PostMapping("/orderdetails")
  public OrderDetail save(@RequestBody OrderDetail orderdetail) throws NotExistByPropertyExeption {
    return orderDetailService.save(orderdetail);
  }


  @PutMapping("/orderdetails/{orderId}/{productId}")
  public OrderDetail update(@PathVariable(value = "orderId") int orderId,
      @PathVariable(value = "productId") int productId, @Valid @RequestBody OrderDetail orderdetail)
      throws NotFoundException {

    orderdetail.setId(new OrderDetailIdentity(orderId, productId));
    return orderDetailService.update(orderdetail);
  }


  @DeleteMapping("/orderdetails/{orderId}/{productId}")
  public void delete(@PathVariable(value = "orderId") int orderId,
      @PathVariable(value = "productId") int productId)
      throws NotFoundException {

    OrderDetail deletedOrderDetail = orderDetailService
        .findById(new OrderDetailIdentity(orderId, productId));
    orderDetailService.delete(deletedOrderDetail);
  }
}

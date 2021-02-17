package com.example.northwind.business.abstracts;

import com.example.northwind.entities.concretes.Order;
import com.example.northwind.entities.concretes.OrderDetail;
import com.example.northwind.exceptions.DeletingErrorByRelationException;
import com.example.northwind.exceptions.NotExistByPropertyExeption;
import com.example.northwind.exceptions.NotFoundException;
import java.util.List;

public interface IOrderService {

  Order save(Order order);

  Order update(Order order) throws NotFoundException;

  Order findById(int orderId) throws NotFoundException;

  void delete(Order order) throws DeletingErrorByRelationException;

  void saveOrderDetail(Order order);

  void order(Order order) throws NotFoundException, NotExistByPropertyExeption;

  List<OrderDetail> getAllOrderCard(Order order);

}

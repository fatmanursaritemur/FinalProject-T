package com.example.northwind.business.abstracts;

import com.example.northwind.entities.concretes.OrderDetail;
import com.example.northwind.entities.concretes.OrderDetailIdentity;
import com.example.northwind.exceptions.NotExistByPropertyExeption;
import com.example.northwind.exceptions.NotFoundException;
import java.util.List;

public interface IOrderDetailService {

  OrderDetail save(OrderDetail orderDetail) throws NotExistByPropertyExeption;

  OrderDetail update(OrderDetail orderDetail) throws NotFoundException;

  OrderDetail findById(OrderDetailIdentity orderDetailIdentity) throws NotFoundException;

  void delete(OrderDetail orderDetail);

  List<OrderDetail> getAllOrderDetailByOrderId(OrderDetail orderDetail);
}

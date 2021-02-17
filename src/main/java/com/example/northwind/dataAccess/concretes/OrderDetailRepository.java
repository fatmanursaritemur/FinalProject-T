package com.example.northwind.dataAccess.concretes;

import com.example.northwind.entities.concretes.OrderDetail;
import com.example.northwind.entities.concretes.OrderDetailIdentity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailIdentity> {

  List<OrderDetail> findAllByIdOrderId(int orderId);
}

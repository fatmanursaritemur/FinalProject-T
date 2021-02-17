package com.example.northwind.business.abstracts;

import com.example.northwind.entities.concretes.Card;
import com.example.northwind.entities.concretes.Order;
import com.example.northwind.entities.concretes.OrderDetail;
import com.example.northwind.entities.concretes.Product;
import java.util.Random;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)

public abstract class OrderDetailMapper {

  public static final OrderDetailMapper INSTANCE = Mappers.getMapper(OrderDetailMapper.class);

  @Mapping(target = "id.productId", source = "product.id")
  @Mapping(target = "id.orderId", source = "order.id")
  @Mapping(target = "unitPrice", expression = "java(getUpdateUnitPrice(product.getUnitPrice()))")
  public abstract OrderDetail getOrderDetail(Order order, Card card, Product product); // card quantity için


  Double getUpdateUnitPrice(Double unitPrice) {
    Random random = new Random();
    return unitPrice + random.nextInt(5);
  }
}

package com.example.northwind.business.concretes;

import com.example.northwind.business.abstracts.ICardService;
import com.example.northwind.business.abstracts.IOrderDetailService;
import com.example.northwind.business.abstracts.IOrderService;
import com.example.northwind.business.abstracts.IProductService;
import com.example.northwind.business.abstracts.OrderDetailMapper;
import com.example.northwind.dataAccess.concretes.OrderRepository;
import com.example.northwind.entities.concretes.Order;
import com.example.northwind.entities.concretes.OrderDetail;
import com.example.northwind.exceptions.DeletingErrorByRelationException;
import com.example.northwind.exceptions.NotExistByPropertyExeption;
import com.example.northwind.exceptions.NotFoundException;
import com.example.northwind.business.utilities.UpdateColumnUtil;
import java.util.List;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderManager implements IOrderService {

  @Autowired
  OrderRepository orderRepository;

  @Autowired
  ICardService cardService;

  @Autowired
  IOrderDetailService orderDetailService;

  @Autowired
  IProductService productService;

  @Autowired
  UpdateColumnUtil updateColumnUtil;

  @Override
  public Order save(Order order) {
    return orderRepository.save(order);
  }


  @Override
  public void saveOrderDetail(Order order) {
    getAllOrderCard(order).forEach(orderDetail -> {
      try {
        orderDetailService.save(orderDetail);
      } catch (NotExistByPropertyExeption notExistByPropertyExeption) {
        notExistByPropertyExeption.printStackTrace();
      }
    });

  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class) //rollback ile order kaydedilmiş olsa bile geri alıyor
  public void order(Order order) throws NotExistByPropertyExeption {
    save(order);
    saveOrderDetail(order);
    cardService.deleteCardsByCustomer(order.getCustomerId());
  }


  @Override
  @SneakyThrows
  public List<OrderDetail> getAllOrderCard(Order order) {
    return cardService
        .getCardsByCustomer(order.getCustomerId())
        .stream()
        .map(card -> {
          return OrderDetailMapper.INSTANCE.getOrderDetail(order, card,
              productService.findById(card.getId().getProductId()));
        }).collect(Collectors.toList());
  }

  @Override
  public Order update(Order order) throws NotFoundException {
    Order target = orderRepository.findById(order.getId())
        .orElseThrow(() -> new NotFoundException(Order.class.getSimpleName(), String.valueOf(order.getId())));

    BeanUtils.copyProperties(order, target,
        updateColumnUtil.getNullPropertyNames(order)); // partial update
    return orderRepository.save(target);
  }

  @Override
  public Order findById(int orderId) throws NotFoundException {
    return orderRepository.findById(orderId)
        .orElseThrow(() -> new NotFoundException(Order.class.getSimpleName(), String.valueOf(orderId)));
  }

  @Override
  public void delete(Order order) throws DeletingErrorByRelationException {
    try {
      orderRepository.delete(order);
    } catch (DataIntegrityViolationException e) {
      throw new DeletingErrorByRelationException(Order.class.getSimpleName(), order.getId());
    }
  }


}

package com.example.northwind.business.concretes;

import com.example.northwind.business.abstracts.IOrderDetailService;
import com.example.northwind.dataAccess.concretes.OrderDetailRepository;
import com.example.northwind.entities.concretes.Order;
import com.example.northwind.entities.concretes.OrderDetail;
import com.example.northwind.entities.concretes.OrderDetailIdentity;
import com.example.northwind.exceptions.NotExistByPropertyExeption;
import com.example.northwind.exceptions.NotFoundException;
import com.example.northwind.business.utilities.UpdateColumnUtil;
import java.util.List;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailManager implements IOrderDetailService {

  @Autowired
  OrderDetailRepository orderDetailRepository;


  @Autowired
  UpdateColumnUtil updateColumnUtil;

  @Override
  public OrderDetail save(OrderDetail orderDetail) throws NotExistByPropertyExeption {
    try {
      return orderDetailRepository.save(orderDetail);
    } catch (ConstraintViolationException e) {
      throw new NotExistByPropertyExeption(OrderDetail.class.getSimpleName(), "4", "222");
    }
  }


  @Override
  public OrderDetail update(OrderDetail orderDetail) throws NotFoundException {
    OrderDetail target = orderDetailRepository.findById(orderDetail.getId())
        .orElseThrow(() -> new NotFoundException(Order.class.getSimpleName(),
            String.valueOf(orderDetail.getId())
        ));

    BeanUtils.copyProperties(orderDetail, target,
        updateColumnUtil.getNullPropertyNames(orderDetail)); // partial update
    return orderDetailRepository.save(target);
  }

  @Override
  public OrderDetail findById(OrderDetailIdentity orderDetailIdentity) throws NotFoundException {
    return orderDetailRepository.findById(orderDetailIdentity)
        .orElseThrow(() -> new NotFoundException(Order.class.getSimpleName(),
            String.valueOf( orderDetailIdentity.getOrderId())));
  }

  @Override
  public void delete(OrderDetail orderDetail) {

    orderDetailRepository.delete(orderDetail);
  }

  @Override
  public List<OrderDetail> getAllOrderDetailByOrderId(OrderDetail orderDetail) {
    return orderDetailRepository.findAllByIdOrderId(orderDetail.getId().getOrderId()); // hata veriyor mu bak!!!
  }


}

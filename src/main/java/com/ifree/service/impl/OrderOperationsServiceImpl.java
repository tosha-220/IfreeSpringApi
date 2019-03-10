package com.ifree.service.impl;

import com.ifree.exceptions.OrderNotFoundException;
import com.ifree.repositories.OrderOperationRepository;
import com.ifree.repositories.entities.Order;
import com.ifree.service.OrderOperationsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderOperationsServiceImpl implements OrderOperationsService {

  private final OrderOperationRepository orderOperationRepository;


  @Autowired
  public OrderOperationsServiceImpl(OrderOperationRepository orderOperationRepository) {
    this.orderOperationRepository = orderOperationRepository;
  }

  @Override
  public Order findOrderById(Long id) {
    log.trace("Start finding order by id {}", id);
    return orderOperationRepository.findById(id)
        .orElseThrow(() -> new OrderNotFoundException("Order not found by id " + id));
  }

  @Override
  public void saveOrder(Order order) {
    log.trace("Start saving order {}", order);
    orderOperationRepository.save(order);
  }

}

package com.ifree.service;

import com.ifree.repositories.entities.Order;

public interface OrderOperationsService {

  Order findOrderById(Long id);

  void saveOrder(Order order);

}

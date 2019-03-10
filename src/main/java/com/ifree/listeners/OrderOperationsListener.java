package com.ifree.listeners;

import com.ifree.repositories.entities.Order;
import com.ifree.service.OrderOperationsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderOperationsListener {

  private final OrderOperationsService orderOperationsService;

  @Autowired
  public OrderOperationsListener(OrderOperationsService orderOperationsService) {
    this.orderOperationsService = orderOperationsService;
  }


  @RabbitListener(queues = "${api.queueName}")
  public void onYangOperationMessage(Order order) {
    log.trace("Order from rabbitMq {}", order);
    orderOperationsService.saveOrder(order);
  }
}
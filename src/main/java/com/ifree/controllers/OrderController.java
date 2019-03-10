package com.ifree.controllers;

import static com.ifree.constants.Constants.SAVING_STARTED_MESSAGE;

import com.ifree.repositories.entities.Order;
import com.ifree.service.OrderOperationsService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/order")
public class OrderController {

  private final OrderOperationsService orderOperationsService;
  private final AmqpTemplate amqpTemplate;

  @Value("${api.queueName}")
  private String queueName;

  @Autowired
  public OrderController(OrderOperationsService orderOperationsService,
      AmqpTemplate amqpTemplate) {
    this.orderOperationsService = orderOperationsService;
    this.amqpTemplate = amqpTemplate;
  }

  @GetMapping(path = "/{id}")
  public Order getOrder(@PathVariable("id") Long id) {
    return orderOperationsService.findOrderById(id);
  }


  @PostMapping
  public String saveOrder(@RequestBody Order order) {
    amqpTemplate.convertAndSend(queueName, order);
    return SAVING_STARTED_MESSAGE;
  }

}

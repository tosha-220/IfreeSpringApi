package com.ifree.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ifree.exceptions.OrderNotFoundException;
import com.ifree.repositories.OrderOperationRepository;
import com.ifree.repositories.entities.BooksOrder;
import com.ifree.repositories.entities.Order;
import com.ifree.service.impl.OrderOperationsServiceImpl;
import java.util.Optional;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.ArgumentCaptor;


public class OrderOperationsServiceTest {

  private static OrderOperationRepository orderOperationRepository;

  private static OrderOperationsService orderOperationsService;

  @BeforeClass
  public static void before() {
    orderOperationRepository = mock(OrderOperationRepository.class);
    orderOperationsService = new OrderOperationsServiceImpl(orderOperationRepository);
  }


  @Test
  public void testSaveOrder() {
    Order order = getOrder();
    orderOperationsService.saveOrder(order);
    ArgumentCaptor<Order> argumentCaptor =
        ArgumentCaptor.forClass(Order.class);
    verify(orderOperationRepository, atLeastOnce())
        .save(argumentCaptor.capture());

    Order actualOrder = argumentCaptor.getValue();

    assertThat(order).isEqualTo(actualOrder);

  }

  @Test
  public void testGetOrder() {
    Order order = getOrder();
    Optional<Order> optional = Optional.of(order);
    when(orderOperationRepository.findById(anyLong())).thenReturn(optional);
    Order actualOrder = orderOperationsService.findOrderById(1L);

    verify(orderOperationRepository, atLeastOnce())
        .findById(1L);
    assertThat(order).isEqualTo(actualOrder);

  }

  @Test(expected = OrderNotFoundException.class)
  public void testGetOrderIfNoFound() {
    when(orderOperationRepository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
    orderOperationsService.findOrderById(1L);
  }

  private Order getOrder() {
    BooksOrder order = new BooksOrder();
    order.setAuthor("test_author");
    order.setCountOfPages(10);
    order.setTitle("test_title");
    return order;
  }

}

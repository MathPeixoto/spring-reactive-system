package com.mpeixoto.order.controller;

import com.mpeixoto.order.domain.Order;
import com.mpeixoto.order.domain.OrderStatus;
import com.mpeixoto.order.services.OrderService;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/orders")
public class OrderController {
  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping
  public Order create(@RequestBody Order order) {
    Order processedOrder = orderService.createOrder(order);
    if (OrderStatus.FAILURE.equals(processedOrder.getOrderStatus())) {
      throw new RuntimeException("Order processing failed, please try again later.");
    }
    return processedOrder;
  }

  @GetMapping
  public List<Order> getAll() {
    return orderService.getOrders();
  }
}

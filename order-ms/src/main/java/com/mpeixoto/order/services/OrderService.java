package com.mpeixoto.order.services;

import com.mpeixoto.order.domain.Order;
import com.mpeixoto.order.domain.OrderStatus;
import com.mpeixoto.order.repository.OrderRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

  private final OrderRepository orderRepository;
  private final RestTemplate restTemplate;

  @Value("${gateway.inventory-url}")
  private String inventoryServiceUrl;

  @Value("${gateway.shipping-url}")
  private String shippingServiceUrl;

  public OrderService(OrderRepository orderRepository, RestTemplate restTemplate) {
    this.orderRepository = orderRepository;
    this.restTemplate = restTemplate;
  }

  public Order createOrder(Order order) {
    boolean success = true;
    Order savedOrder = orderRepository.save(order);

    try {
      restTemplate.postForObject(inventoryServiceUrl, order, Order.class);
    } catch (Exception ex) {
      success = false;
    }

    Order shippingResponse = null;
    try {
      shippingResponse = restTemplate.postForObject(
          shippingServiceUrl, order, Order.class);
    } catch (Exception ex) {
      success = false;
      HttpEntity<Order> deleteRequest = new HttpEntity<>(order);
      restTemplate.exchange(inventoryServiceUrl, HttpMethod.DELETE, deleteRequest, Order.class);
    }

    if (success) {
      savedOrder.setOrderStatus(OrderStatus.SUCCESS);
      assert shippingResponse != null;
      savedOrder.setShippingDate(shippingResponse.getShippingDate());
    } else {
      savedOrder.setOrderStatus(OrderStatus.FAILURE);
    }
    return orderRepository.save(savedOrder);
  }

  public List<Order> getOrders() {
    return orderRepository.findAll();
  }
}

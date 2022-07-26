package com.mpeixoto.shipping.services;

import com.mpeixoto.shipping.domain.Order;
import com.mpeixoto.shipping.domain.OrderStatus;
import com.mpeixoto.shipping.domain.Shipment;
import com.mpeixoto.shipping.repository.ShippingRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.stereotype.Service;

@Service
public class ShippingService {

  private final ShippingRepository shippingRepository;

  public ShippingService(ShippingRepository shippingRepository) {
    this.shippingRepository = shippingRepository;
  }

  public Order handleOrder(Order order) {
    LocalDate shippingDate;
    if (LocalTime.now().isAfter(LocalTime.parse("10:00"))
        && LocalTime.now().isBefore(LocalTime.parse("18:00"))) {
      shippingDate = LocalDate.now().plusDays(1);
    } else {
      throw new RuntimeException("The current time is off the limits to place order.");
    }
    shippingRepository.save(new Shipment()
        .setAddress(order.getShippingAddress())
        .setShippingDate(shippingDate));
    return order.setShippingDate(shippingDate)
        .setOrderStatus(OrderStatus.SUCCESS);
  }
}

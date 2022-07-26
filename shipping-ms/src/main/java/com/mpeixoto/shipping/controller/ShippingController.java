package com.mpeixoto.shipping.controller;

import com.mpeixoto.shipping.domain.Order;
import com.mpeixoto.shipping.services.ShippingService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/shipments")
public class ShippingController {
  private final ShippingService shippingService;

  public ShippingController(ShippingService shippingService) {
    this.shippingService = shippingService;
  }

  @PostMapping
  public Order process(@RequestBody Order order) {
    return shippingService.handleOrder(order);
  }
}

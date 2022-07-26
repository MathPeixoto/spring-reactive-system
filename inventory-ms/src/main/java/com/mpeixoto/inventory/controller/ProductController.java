package com.mpeixoto.inventory.controller;

import com.mpeixoto.inventory.domain.Order;
import com.mpeixoto.inventory.domain.Product;
import com.mpeixoto.inventory.services.ProductService;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/products")
public class ProductController {
  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping
  public List<Product> getAllProducts() {
    return productService.getProducts();
  }

  @PostMapping
  public Order processOrder(@RequestBody Order order) {
    return productService.handleOrder(order);
  }

  @DeleteMapping
  public Order revertOrder(@RequestBody Order order) {
    return productService.revertOrder(order);
  }
}

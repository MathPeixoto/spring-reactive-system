package com.mpeixoto.inventory.services;

import com.mpeixoto.inventory.domain.Order;
import com.mpeixoto.inventory.domain.OrderStatus;
import com.mpeixoto.inventory.domain.Product;
import com.mpeixoto.inventory.repository.ProductRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Transactional
  public Order handleOrder(Order order) {
    order
        .getLineItems()
        .forEach(
            lineItem -> {
              Product product =
                  productRepository
                      .findById(lineItem.getProductId())
                      .orElseThrow(
                          () ->
                              new RuntimeException(
                                  "Could not find the product: " + lineItem.getProductId()));
              if (product.getStock() >= lineItem.getQuantity()) {
                product.setStock(product.getStock() - lineItem.getQuantity());
                productRepository.save(product);
              } else {
                throw new RuntimeException("Product is out of stock: " + lineItem.getProductId());
              }
            });
    return order.setOrderStatus(OrderStatus.SUCCESS);
  }

  @Transactional
  public Order revertOrder(Order order) {
    order
        .getLineItems()
        .forEach(
            l -> {
              Product p =
                  productRepository
                      .findById(l.getProductId())
                      .orElseThrow(
                          () ->
                              new RuntimeException(
                                  "Could not find the product: " + l.getProductId()));
              p.setStock(p.getStock() + l.getQuantity());
              productRepository.save(p);
            });
    return order.setOrderStatus(OrderStatus.SUCCESS);
  }

  public List<Product> getProducts() {
    return productRepository.findAll();
  }
}

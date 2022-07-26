package com.mpeixoto.order.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mpeixoto.order.utils.ObjectIdSerializer;
import java.time.LocalDate;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Order {

  @Id
  @JsonSerialize(using = ObjectIdSerializer.class)
  private ObjectId id;
  private String userId;
  private Long total;
  private String paymentMode;
  private Address shippingAddress;
  private LocalDate shippingDate;
  private OrderStatus orderStatus;
  private String responseMessage;

  public Order setShippingDate(LocalDate shippingDate) {
    this.shippingDate = shippingDate;
    return this;
  }

  public Order setOrderStatus(OrderStatus orderStatus) {
    this.orderStatus = orderStatus;
    return this;
  }

  public Order setResponseMessage(String responseMessage) {
    this.responseMessage = responseMessage;
    return this;
  }

}

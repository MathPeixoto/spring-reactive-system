package com.mpeixoto.inventory.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mpeixoto.inventory.utils.ObjectIdSerializer;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Product {

  @JsonSerialize(using = ObjectIdSerializer.class)
  private ObjectId id;

  private String name;
  private Long price;
  private Integer stock;
}

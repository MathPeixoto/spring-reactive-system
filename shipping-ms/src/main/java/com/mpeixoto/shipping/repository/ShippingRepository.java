package com.mpeixoto.shipping.repository;

import com.mpeixoto.shipping.domain.Shipment;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingRepository extends MongoRepository<Shipment, ObjectId> {

}

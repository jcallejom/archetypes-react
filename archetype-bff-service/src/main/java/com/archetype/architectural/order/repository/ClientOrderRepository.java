package com.archetype.architectural.order.repository;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.archetype.architectural.order.entity.ClientOrder;

@Repository
//public interface PurchaseOrderRepository extends ReactiveCrudRepository<PurchaseOrder, UUID> {
	public interface ClientOrderRepository  extends R2dbcRepository<ClientOrder, UUID> {

}

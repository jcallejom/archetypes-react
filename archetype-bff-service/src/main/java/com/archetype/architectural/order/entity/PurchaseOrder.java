package com.archetype.architectural.order.entity;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import com.archetype.architectural.enums.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
//@Table
@Table
public class PurchaseOrder {
//public class PurchaseOrder implements Persistable<UUID>{
    @Id
    private UUID id;
    private Integer userId;
    private Integer productId;
    private Double price;
    private OrderStatus status;
//	@Override
//	public boolean isNew() {
//		// TODO Auto-generated method stub
//		return true;
//	}

}
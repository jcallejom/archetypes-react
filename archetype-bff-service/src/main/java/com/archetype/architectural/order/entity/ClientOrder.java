package com.archetype.architectural.order.entity;

import java.util.UUID;

import org.springframework.data.annotation.Id;
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
public class ClientOrder {

    @Id
    private UUID id;
   	private String clientId;
	private String userId;
	private String cardId;
    private OrderStatus status;

}
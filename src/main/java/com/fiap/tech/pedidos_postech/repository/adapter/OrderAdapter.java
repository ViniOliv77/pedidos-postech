package com.fiap.tech.pedidos_postech.repository.adapter;

import com.fiap.tech.pedidos_postech.domain.order.Order;
import com.fiap.tech.pedidos_postech.repository.model.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@Mapper
public interface OrderAdapter {

    OrderAdapter INSTANCE = Mappers.getMapper(OrderAdapter.class);

    OrderEntity toEntity(Order order);

    Order fromEntity(OrderEntity orderEntity);

    Optional<Order> fromEntity(Optional<OrderEntity> orderEntity);

}

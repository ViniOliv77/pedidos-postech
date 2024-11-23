package com.fiap.tech.pedidos_postech.order.adapter;

import com.fiap.tech.pedidos_postech.domain.order.Order;
import com.fiap.tech.pedidos_postech.domain.order.enums.Status;
import com.fiap.tech.pedidos_postech.order.dto.OrderDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderAdapter {

    OrderAdapter INSTANCE = Mappers.getMapper(OrderAdapter.class);

    OrderDTO fromDomain(Order domain);

    Order toDomain(OrderDTO dto);

    @AfterMapping
    default void afterMapping(@MappingTarget Order order) {
        if (order.getStatus() == null) {
            order.setStatus(Status.REQUESTED);
        }
    }

}
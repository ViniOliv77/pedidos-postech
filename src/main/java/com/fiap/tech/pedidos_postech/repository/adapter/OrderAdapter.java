package com.fiap.tech.pedidos_postech.repository.adapter;

import com.fiap.tech.pedidos_postech.domain.order.Order;
import com.fiap.tech.pedidos_postech.repository.model.OrderEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderAdapter {

    OrderAdapter INSTANCE = Mappers.getMapper(OrderAdapter.class);

    @BeanMapping(builder = @Builder(disableBuilder = true))
    OrderEntity toEntity(final Order order);

    @BeanMapping(builder = @Builder(disableBuilder = true))
    Order fromEntity(final OrderEntity orderEntity);

    @AfterMapping
    default void afterMapping(@MappingTarget final OrderEntity orderEntity) {
        orderEntity.getItems().forEach(orderItem -> orderItem.setOrder(orderEntity));
    }

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    OrderEntity update(@MappingTarget OrderEntity persistedOrder, Order updatedOrder);

}

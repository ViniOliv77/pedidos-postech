package com.fiap.tech.pedidos_postech.order.adapter;

import com.fiap.tech.pedidos_postech.domain.order.Order;
import com.fiap.tech.pedidos_postech.domain.order.enums.Status;
import com.fiap.tech.pedidos_postech.order.dto.OrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = {Status.class})
public abstract class OrderAdapter {

    public abstract OrderDTO fromDomain(Order domain);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "items", source = "dto.items")
    @Mapping(target = "status", defaultExpression = "java(Status.REQUESTED)")
    public abstract Order toDomain(OrderDTO dto);

}
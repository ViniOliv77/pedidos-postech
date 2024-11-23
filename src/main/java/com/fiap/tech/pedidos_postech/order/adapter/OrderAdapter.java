package com.fiap.tech.pedidos_postech.order.adapter;

import com.fiap.tech.pedidos_postech.order.dto.OrderDTO;
import com.fiap.tech.pedidos_postech.domain.order.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderAdapter {

    OrderAdapter INSTANCE = Mappers.getMapper(OrderAdapter.class);

    OrderDTO fromDomain(Order domain);

    Order toDomain(OrderDTO dto);

}
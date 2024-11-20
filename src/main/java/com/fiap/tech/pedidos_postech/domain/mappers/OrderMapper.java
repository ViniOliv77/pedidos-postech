package com.fiap.tech.pedidos_postech.domain.mappers;

import com.fiap.tech.pedidos_postech.application.dto.OrderDTO;
import com.fiap.tech.pedidos_postech.domain.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDTO toDTO(Order order);
    Order toEntity(OrderDTO dto);
}
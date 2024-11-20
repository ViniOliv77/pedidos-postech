package com.fiap.tech.pedidos_postech.application;

import com.fiap.tech.pedidos_postech.application.dto.OrderDTO;
import com.fiap.tech.pedidos_postech.domain.mappers.OrderMapper;
import com.fiap.tech.pedidos_postech.domain.model.Order;
import com.fiap.tech.pedidos_postech.infra.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = orderMapper.toEntity(orderDTO);
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());
        order = orderRepository.save(order);
        return orderMapper.toDTO(order);
    }
}
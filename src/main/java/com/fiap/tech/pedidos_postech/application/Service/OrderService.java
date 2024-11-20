package com.fiap.tech.pedidos_postech.application.Service;

import com.fiap.tech.pedidos_postech.application.dto.OrderDTO;
import com.fiap.tech.pedidos_postech.domain.mappers.OrderMapper;
import com.fiap.tech.pedidos_postech.domain.model.Order;
import com.fiap.tech.pedidos_postech.infra.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

    @Service
    public class OrderService {

        @Autowired
        private OrderRepository orderRepository;

        @Autowired
        private OrderMapper orderMapper;

        public OrderDTO createOrder(OrderDTO orderDTO) {
            Order order = orderMapper.toEntity(orderDTO);
            Order savedOrder = orderRepository.save(order);
            return orderMapper.toDTO(savedOrder);
        }

        public OrderDTO getOrder(Long id) {
            return orderRepository.findById(id)
                    .map(orderMapper::toDTO)
                    .orElse(null);
        }
    }


package com.fiap.tech.pedidos_postech.core.business;

import com.fiap.tech.pedidos_postech.domain.order.Order;
import com.fiap.tech.pedidos_postech.order.dto.OrderDTO;
import jakarta.validation.Valid;

import java.util.Set;

public interface OrderBusiness {

    Order createOrder(Order order);

    Order getOrder(Long id);

    Set<Order> getOrders();

    Order putOrder(Long id, Order order);

    Order cancelOrder(Long id);

}
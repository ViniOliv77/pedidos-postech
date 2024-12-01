package com.fiap.tech.pedidos_postech.core.business;

import com.fiap.tech.pedidos_postech.domain.order.Order;

import java.util.Set;

public interface OrderBusiness {

    Order createOrder(Order order);

    Order getOrder(Long id);

    Set<Order> getOrders();

    Order putOrder(Long id, Order order, Boolean sendMessage);

    Order cancelOrder(Long id);

}

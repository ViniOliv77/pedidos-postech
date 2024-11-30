package com.fiap.tech.pedidos_postech.core.repository;

import com.fiap.tech.pedidos_postech.domain.order.Order;

import java.util.Optional;
import java.util.Set;

public interface OrderRepository {

    Order save(Order order);

    Order update(Order order);

    Optional<Order> findById(Long id);

    Set<Order> findAll();

}

package com.fiap.tech.pedidos_postech.order.business;

import com.fiap.tech.pedidos_postech.core.business.OrderBusiness;
import com.fiap.tech.pedidos_postech.core.exception.BusinessException;
import com.fiap.tech.pedidos_postech.core.exception.NotFoundException;
import com.fiap.tech.pedidos_postech.core.repository.OrderRepository;
import com.fiap.tech.pedidos_postech.domain.order.Order;
import com.fiap.tech.pedidos_postech.domain.order.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class OrderBusinessImpl implements OrderBusiness {

    private final OrderRepository orderRepository;

    @Override
    public Order createOrder(final Order order) {
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        return orderRepository.save(order);
    }

    @Override
    public Order getOrder(final Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order not found"));
    }

    @Override
    public Set<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order putOrder(final Long id, final Order order) {
        final Order persistedOrder = getOrder(id);

        if (persistedOrder.getStatus().equals(Status.CANCELLED) ||
                persistedOrder.getStatus().equals(Status.FINISHED)) {
            throw new BusinessException("Terminate order can not be updated");
        }

        order.setId(id);
        order.setUpdatedAt(LocalDateTime.now());

        return orderRepository.save(order);
    }

    @Override
    public Order cancelOrder(final Long id) {
        final Order persistedOrder = getOrder(id);

        if (persistedOrder.getStatus().equals(Status.CANCELLED) ||
                persistedOrder.getStatus().equals(Status.FINISHED) ||
                persistedOrder.getStatus().equals(Status.SENT)) {
            throw new BusinessException("Terminate order can not be updated");
        }

        persistedOrder.setStatus(Status.CANCELLED);

        return orderRepository.save(persistedOrder);
    }

}


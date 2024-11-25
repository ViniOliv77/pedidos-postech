package com.fiap.tech.pedidos_postech.order.business;

import com.fiap.tech.pedidos_postech.core.business.OrderBusiness;
import com.fiap.tech.pedidos_postech.core.exception.BusinessException;
import com.fiap.tech.pedidos_postech.core.exception.NotFoundException;
import com.fiap.tech.pedidos_postech.core.queue.LogisticQueueProducer;
import com.fiap.tech.pedidos_postech.core.queue.StorageQueueProducer;
import com.fiap.tech.pedidos_postech.core.repository.OrderRepository;
import com.fiap.tech.pedidos_postech.domain.order.Order;
import com.fiap.tech.pedidos_postech.domain.order.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class OrderBusinessImpl implements OrderBusiness {

    private final OrderRepository orderRepository;

    private final StorageQueueProducer storageQueueProducer;

    private final LogisticQueueProducer logisticQueueProducer;

    @Override
    public Order createOrder(final Order order) {
        Order newOrder = orderRepository.save(order);

        storageQueueProducer.publish(newOrder, false);
        logisticQueueProducer.publish(newOrder, false);

        return newOrder;
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

        storageQueueProducer.publish(persistedOrder, true);
        storageQueueProducer.publish(order, false);
        logisticQueueProducer.publish(order, false);

        return orderRepository.save(order);
    }

    @Override
    public Order cancelOrder(final Long id) {
        final Order persistedOrder = getOrder(id);

        if (persistedOrder.getStatus().equals(Status.CANCELLED) ||
                persistedOrder.getStatus().equals(Status.FINISHED)) {
            throw new BusinessException("Terminate order can not be updated");
        }

        persistedOrder.setStatus(Status.CANCELLED);

        storageQueueProducer.publish(persistedOrder, true);
        logisticQueueProducer.publish(persistedOrder, true);

        return orderRepository.save(persistedOrder);
    }

}


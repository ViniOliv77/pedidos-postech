package com.fiap.tech.pedidos_postech.order.business;

import com.fiap.tech.pedidos_postech.core.business.OrderBusiness;
import com.fiap.tech.pedidos_postech.core.exception.BusinessException;
import com.fiap.tech.pedidos_postech.core.exception.NotFoundException;
import com.fiap.tech.pedidos_postech.core.queue.LogisticQueueProducer;
import com.fiap.tech.pedidos_postech.core.queue.ProductQueueProducer;
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

    private final ProductQueueProducer productQueueProducer;

    private final LogisticQueueProducer logisticQueueProducer;

    @Override
    public Order createOrder(final Order order) {
        Order newOrder = orderRepository.save(order);

        productQueueProducer.publish(newOrder, false);
        logisticQueueProducer.publish(newOrder, false);

        return newOrder;
    }

    @Override
    public Order getOrder(final Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Pedido não encontrado"));
    }

    @Override
    public Set<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order putOrder(final Long id, final Order order, final Boolean fromController) {
        final Order persistedOrder = getOrder(id);

        if (fromController && !Status.isCancellable(persistedOrder.getStatus())) {
            throw new BusinessException(
                    "Pedido em transferencia ou finalizado não pode ser alterado");
        }

        order.setId(id);

        if (fromController) {
            productQueueProducer.publish(persistedOrder, true);
            productQueueProducer.publish(order, false);

            logisticQueueProducer.publish(persistedOrder, true);
            logisticQueueProducer.publish(order, false);
        }

        return orderRepository.update(order);
    }

    @Override
    public Order cancelOrder(final Long id) {
        final Order persistedOrder = getOrder(id);

        if (!Status.isCancellable(persistedOrder.getStatus())) {
            throw new BusinessException(
                    "Pedido em transferencia ou finalizado não pode ser alterado");
        }

        persistedOrder.setStatus(Status.CANCELLED);

        productQueueProducer.publish(persistedOrder, true);
        logisticQueueProducer.publish(persistedOrder, true);

        return orderRepository.update(persistedOrder);
    }

}


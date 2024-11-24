package com.fiap.tech.pedidos_postech.core.queue;

import com.fiap.tech.pedidos_postech.domain.order.Order;

public interface OrderQueueProducer {

    void publish(Order order);

}

package com.fiap.tech.pedidos_postech.mock;

import com.fiap.tech.pedidos_postech.domain.order.Order;
import com.fiap.tech.pedidos_postech.domain.order.enums.Status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderMock {

    public static Order create() {
        final List<Order.OrderItem> items = new ArrayList<>();

        items.add(Order.OrderItem.builder()
                          .productId(1L)
                          .quantity(10)
                          .unitPrice(1.5)
                          .build()
        );

        return Order.builder()
                .clientId(1L)
                .orderDate(LocalDateTime.now())
                .deliveryDate(LocalDateTime.now().plusDays(5))
                .note("Sample order note")
                .status(Status.REQUESTED)
                .items(items)
                .build();
    }

}

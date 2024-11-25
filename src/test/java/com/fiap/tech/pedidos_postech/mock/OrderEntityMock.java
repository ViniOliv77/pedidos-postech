package com.fiap.tech.pedidos_postech.mock;

import com.fiap.tech.pedidos_postech.domain.order.enums.Status;
import com.fiap.tech.pedidos_postech.repository.model.ItemEntity;
import com.fiap.tech.pedidos_postech.repository.model.OrderEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderEntityMock {

    public static OrderEntity create() {
        final List<ItemEntity> items = new ArrayList<>();

        items.add(ItemEntity.builder()
                          .productId(1L)
                          .quantity(10)
                          .unitPrice(1.5)
                          .build()
        );

        return OrderEntity.builder()
                .clientId(1L)
                .orderDate(LocalDateTime.now())
                .deliveryDate(LocalDateTime.now().plusDays(5))
                .note("Sample order note")
                .status(Status.REQUESTED)
                .items(items)
                .build();
    }

}

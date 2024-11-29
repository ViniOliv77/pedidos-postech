package com.fiap.tech.pedidos_postech.usecase;

import com.fiap.tech.pedidos_postech.mock.OrderEntityMock;
import com.fiap.tech.pedidos_postech.repository.jpa.OrderJpaRepository;
import com.fiap.tech.pedidos_postech.repository.model.OrderEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class OrderJpaRepositoryTest {

    @Autowired
    private OrderJpaRepository orderJpaRepository;

    private OrderEntity orderEntity;

    @BeforeEach
    void setUp() {
        orderEntity = OrderEntityMock.create();
    }

    @Test
    void saveOrder_ShouldReturnSavedOrder() {
        OrderEntity savedOrder = orderJpaRepository.save(orderEntity);

        assertNotNull(savedOrder.getId());
        assertEquals(orderEntity.getClientId(), savedOrder.getClientId());
    }

    @Test
    void findById_ShouldReturnOrder() {
        OrderEntity savedOrder = orderJpaRepository.save(orderEntity);

        Optional<OrderEntity> foundOrder = orderJpaRepository.findById(savedOrder.getId());

        assertTrue(foundOrder.isPresent());
        assertEquals(savedOrder.getId(), foundOrder.get().getId());
    }

    @Test
    void findById_ShouldReturnEmptyIfNotFound() {
        Optional<OrderEntity> foundOrder = orderJpaRepository.findById(999L);

        assertFalse(foundOrder.isPresent());
    }

}

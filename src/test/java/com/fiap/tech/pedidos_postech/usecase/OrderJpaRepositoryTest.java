package com.fiap.tech.pedidos_postech.usecase;

import com.fiap.tech.pedidos_postech.domain.order.Order;
import com.fiap.tech.pedidos_postech.repository.repository.OrderJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class OrderJpaRepositoryTest {

    @Autowired
    private OrderJpaRepository orderJpaRepository;

    private Order order;

    @BeforeEach
    void setUp() {
        order = new Order();
        order.setClientId(1L);
        order.setOrderDate(LocalDateTime.now());
        order.setDeliveryDate(LocalDateTime.now().plusDays(5));
        order.setNote("Test order");
    }

    @Test
    void saveOrder_ShouldReturnSavedOrder() {
        Order savedOrder = orderJpaRepository.save(order);

        assertNotNull(savedOrder.getId());
        assertEquals(order.getClientId(), savedOrder.getClientId());
    }

    @Test
    void findById_ShouldReturnOrder() {
        Order savedOrder = orderJpaRepository.save(order);

        Optional<Order> foundOrder = orderJpaRepository.findById(savedOrder.getId());

        assertTrue(foundOrder.isPresent());
        assertEquals(savedOrder.getId(), foundOrder.get().getId());
    }

    @Test
    void findById_ShouldReturnEmptyIfNotFound() {
        Optional<Order> foundOrder = orderJpaRepository.findById(999L);

        assertFalse(foundOrder.isPresent());
    }
}

package com.fiap.tech.pedidos_postech.usecase;

import com.fiap.tech.pedidos_postech.domain.model.Order;
import com.fiap.tech.pedidos_postech.infra.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

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
        Order savedOrder = orderRepository.save(order);

        assertNotNull(savedOrder.getId());
        assertEquals(order.getClientId(), savedOrder.getClientId());
    }

    @Test
    void findById_ShouldReturnOrder() {
        Order savedOrder = orderRepository.save(order);

        Optional<Order> foundOrder = orderRepository.findById(savedOrder.getId());

        assertTrue(foundOrder.isPresent());
        assertEquals(savedOrder.getId(), foundOrder.get().getId());
    }

    @Test
    void findById_ShouldReturnEmptyIfNotFound() {
        Optional<Order> foundOrder = orderRepository.findById(999L);

        assertFalse(foundOrder.isPresent());
    }
}

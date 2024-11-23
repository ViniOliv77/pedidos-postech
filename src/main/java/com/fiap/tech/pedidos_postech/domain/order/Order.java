package com.fiap.tech.pedidos_postech.domain.order;

import com.fiap.tech.pedidos_postech.domain.order.enums.Status;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long clientId;

    private List<Item> items;

    private LocalDateTime orderDate;

    private LocalDateTime deliveryDate;

    private String note;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Status status;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Item {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long orderId;

        private Long productId;

        private Integer quantity;

        private BigDecimal unitPrice;

    }

}

package com.fiap.tech.pedidos_postech.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long clientId;
    private Long productId;
    private Integer quantity;
    private Double unitPrice;
    private LocalDateTime orderDate;
    private LocalDateTime deliveryDate;
    private String note;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

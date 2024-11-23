package com.fiap.tech.pedidos_postech.repository.model;

import com.fiap.tech.pedidos_postech.domain.order.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long clientId;

    @OneToMany(mappedBy = "order")
    private List<ItemEntity> items;

    private LocalDateTime orderDate;

    private LocalDateTime deliveryDate;

    private String note;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Status status;

}

package com.fiap.tech.pedidos_postech.order.dto;

import com.fiap.tech.pedidos_postech.domain.order.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class OrderMessageDTO {

    private Long id;

    private Long clientId;

    private List<OrderItem> items;

    private LocalDateTime orderDate;

    private LocalDateTime deliveryDate;

    private String note;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Status status;

    private Boolean cancelled;

    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    public static class OrderItem {

        private Long id;

        private Long productId;

        private Integer quantity;

        private Double unitPrice;

    }

}


package com.fiap.tech.pedidos_postech.application.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OrderDTO {
    private Long clientId;
    private Long productId;
    private Integer quantity;
    private Double unitPrice;
    private LocalDateTime orderDate;
    private LocalDateTime deliveryDate;
    private String note;
}
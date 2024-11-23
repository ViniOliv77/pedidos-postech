package com.fiap.tech.pedidos_postech.order.dto;

import com.fiap.tech.pedidos_postech.domain.order.enums.Status;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class OrderDTO {

    private Long id;

    @NotNull
    private Long clientId;

    @NotNull
    private List<OrderItemsDTO> items;

    @NotNull
    private LocalDateTime orderDate;

    @NotNull
    private LocalDateTime deliveryDate;

    @NotBlank
    private String note;

    private Status status;
    @AllArgsConstructor
    @Valid
    public static class OrderItemsDTO {

        @NotNull
        private Long productId;

        @NotNull
        private Integer quantity;

        @NotNull
        private Double unitPrice;

    }

}
package com.fiap.tech.pedidos_postech.order.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fiap.tech.pedidos_postech.domain.order.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class OrderDTO {

    private Long id;

    @NotNull
    private Long clientId;

    @NotNull
    private List<OrderItemDTO> items;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime orderDate;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime deliveryDate;

    @NotBlank
    private String note;

    private Status status;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime updatedAt;

    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    @Getter
    @Setter
    public static class OrderItemDTO {

        private Long id;

        @NotNull
        private Long productId;

        @NotNull
        private Integer quantity;

        @NotNull
        private Double unitPrice;

    }

}


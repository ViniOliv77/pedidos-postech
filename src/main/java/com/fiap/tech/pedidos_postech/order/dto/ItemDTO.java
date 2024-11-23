package com.fiap.tech.pedidos_postech.order.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ItemDTO {
    private Long produtoId;
    private Integer quantidade;
    private BigDecimal valorUnitario;
}
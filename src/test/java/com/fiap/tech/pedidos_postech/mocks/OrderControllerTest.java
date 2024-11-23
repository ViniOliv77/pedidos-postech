package com.fiap.tech.pedidos_postech.mocks;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.tech.pedidos_postech.order.business.OrderBusinessImpl;
import com.fiap.tech.pedidos_postech.order.controller.OrderController;
import com.fiap.tech.pedidos_postech.order.dto.OrderDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @Mock
    private OrderBusinessImpl orderService;

    @InjectMocks
    private OrderController orderController;

    private MockMvc mockMvc;
    private OrderDTO orderDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();

        orderDTO = new OrderDTO();
        orderDTO.setClientId(1L);
        orderDTO.setProductId(101L);
        orderDTO.setQuantity(2);
        orderDTO.setUnitPrice(100.00);
        orderDTO.setOrderDate(LocalDateTime.now());
        orderDTO.setDeliveryDate(LocalDateTime.now().plusDays(5));
        orderDTO.setNote("Sample order note");
    }

    @Test
    void createOrder_ShouldReturnCreated() throws Exception {
        when(orderService.createOrder(orderDTO)).thenReturn(orderDTO);

        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(orderDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.clientId").value(orderDTO.getClientId()))
                .andExpect(jsonPath("$.note").value(orderDTO.getNote()));
    }

    @Test
    void getOrder_ShouldReturnOrder() throws Exception {
        when(orderService.getOrder(1L)).thenReturn(orderDTO);

        mockMvc.perform(get("/api/orders/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clientId").value(orderDTO.getClientId()))
                .andExpect(jsonPath("$.note").value(orderDTO.getNote()));
    }

    @Test
    void getOrder_ShouldReturnNotFound() throws Exception {
        when(orderService.getOrder(1L)).thenReturn(null);

        mockMvc.perform(get("/api/orders/1"))
                .andExpect(status().isNotFound());
    }
}

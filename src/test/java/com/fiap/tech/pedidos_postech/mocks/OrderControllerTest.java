package com.fiap.tech.pedidos_postech.mocks;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.tech.pedidos_postech.domain.order.Order;
import com.fiap.tech.pedidos_postech.mock.OrderMock;
import com.fiap.tech.pedidos_postech.order.business.OrderBusinessImpl;
import com.fiap.tech.pedidos_postech.order.controller.OrderController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    private final Order order = OrderMock.create();

    @Mock
    private OrderBusinessImpl orderService;

    @InjectMocks
    private OrderController orderController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    void createOrder_ShouldReturnCreated() throws Exception {
        when(orderService.createOrder(order)).thenReturn(order);

        mockMvc.perform(post("/api/orders")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(order)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.clientId").value(order.getClientId()))
                .andExpect(jsonPath("$.note").value(order.getNote()));
    }

    @Test
    void getOrder_ShouldReturnOrder() throws Exception {
        when(orderService.getOrder(1L)).thenReturn(order);

        mockMvc.perform(get("/api/orders/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clientId").value(order.getClientId()))
                .andExpect(jsonPath("$.note").value(order.getNote()));
    }

    @Test
    void getOrder_ShouldReturnNotFound() throws Exception {
        when(orderService.getOrder(1L)).thenReturn(null);

        mockMvc.perform(get("/api/orders/1"))
                .andExpect(status().isNotFound());
    }

}

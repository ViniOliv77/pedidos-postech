package com.fiap.tech.pedidos_postech.order.controller;

import com.fiap.tech.pedidos_postech.core.business.OrderBusiness;
import com.fiap.tech.pedidos_postech.order.adapter.OrderAdapter;
import com.fiap.tech.pedidos_postech.order.dto.OrderDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class OrderController {

    private final OrderBusiness orderService;

    private final OrderAdapter orderAdapter = OrderAdapter.INSTANCE;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/order")
    public OrderDTO createOrder(@Valid @RequestBody OrderDTO orderDTO) {
        return orderAdapter.fromDomain(
                orderService.createOrder(
                        orderAdapter.toDomain(orderDTO)
                )
        );
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/order/{id}")
    public OrderDTO getOrder(@PathVariable Long id) {
        return orderAdapter.fromDomain(
                orderService.getOrder(id)
        );
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/order")
    public Set<OrderDTO> getOrders() {
        return orderService.getOrders().stream()
                .map(orderAdapter::fromDomain)
                .collect(Collectors.toSet());
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/api/order/{id}")
    public OrderDTO putOrder(@PathVariable Long id, @Valid @RequestBody OrderDTO orderDTO) {
        return orderAdapter.fromDomain(
                orderService.putOrder(
                        id,
                        orderAdapter.toDomain(orderDTO),
                        true
                )
        );
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/api/order/{id}/cancel")
    public OrderDTO cancelOrder(@PathVariable Long id) {
        return orderAdapter.fromDomain(
                orderService.cancelOrder(id)
        );
    }

}
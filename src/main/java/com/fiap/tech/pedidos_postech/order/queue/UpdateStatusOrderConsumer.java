package com.fiap.tech.pedidos_postech.order.queue;

import com.fiap.tech.pedidos_postech.core.business.OrderBusiness;
import com.fiap.tech.pedidos_postech.domain.order.Order;
import com.fiap.tech.pedidos_postech.domain.order.enums.Status;
import com.fiap.tech.pedidos_postech.order.dto.OrderStatusUpdateDTO;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateStatusOrderConsumer {

    private final OrderBusiness orderBusiness;

    @SqsListener("${queue.order.update.status.name}")
    public void updateStatus(OrderStatusUpdateDTO orderUpdate) {
        Order order = orderBusiness.getOrder(orderUpdate.getId());

        order.setStatus(Status.valueOf(orderUpdate.getStatus()));

        orderBusiness.putOrder(orderUpdate.getId(), order);
    }

}

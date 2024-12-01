package com.fiap.tech.pedidos_postech.order.queue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.tech.pedidos_postech.core.queue.LogisticQueueProducer;
import com.fiap.tech.pedidos_postech.domain.order.Order;
import com.fiap.tech.pedidos_postech.order.adapter.OrderAdapter;
import com.fiap.tech.pedidos_postech.order.dto.OrderMessageDTO;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LogisticQueueProducerImpl implements LogisticQueueProducer {

    private final SqsTemplate sqsTemplate;

    private final ObjectMapper objectMapper;

    private final OrderAdapter orderAdapter = OrderAdapter.INSTANCE;

    @Value("${queue.logistic.name}")
    private String queueUrl;

    @Override
    public void publish(Order order, Boolean cancelled) {
        try {
            OrderMessageDTO message = orderAdapter.toMessage(order);
            message.setCancelled(cancelled);

            sqsTemplate.send(
                    queueUrl, objectMapper.writeValueAsString(message)
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}

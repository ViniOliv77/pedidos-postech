package com.fiap.tech.pedidos_postech.order.queue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.tech.pedidos_postech.core.queue.ProductQueueProducer;
import com.fiap.tech.pedidos_postech.domain.order.Order;
import com.fiap.tech.pedidos_postech.order.dto.StorageDTO;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductQueueProducerImpl implements ProductQueueProducer {

    private final SqsTemplate sqsTemplate;

    private final ObjectMapper objectMapper;

    @Value("${queue.product.name}")
    private String queueUrl;

    @Override
    public void publish(Order order, Boolean cancelled) {
        order.getItems().forEach(item -> {
            try {
                sqsTemplate.send(
                        queueUrl, objectMapper.writeValueAsString(
                                new StorageDTO(
                                        item.getProductId(), item.getQuantity(), cancelled
                                )
                        )
                );
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }
}

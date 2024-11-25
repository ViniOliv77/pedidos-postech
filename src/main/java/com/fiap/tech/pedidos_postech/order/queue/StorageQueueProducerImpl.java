package com.fiap.tech.pedidos_postech.order.queue;

import com.fiap.tech.pedidos_postech.core.queue.StorageQueueProducer;
import com.fiap.tech.pedidos_postech.domain.order.Order;
import com.fiap.tech.pedidos_postech.order.dto.StorageDTO;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StorageQueueProducerImpl implements StorageQueueProducer {

    private final SqsTemplate sqsTemplate;

    @Value("${queue.estoque.name}")
    private String queueUrl;

    @Override
    public void publish(Order order, Boolean cancelled) {
        order.getItems().forEach(item -> {
            sqsTemplate.send(
                    queueUrl, new StorageDTO(
                            item.getProductId(), item.getQuantity(), cancelled
                    )
            );
        });
    }

}
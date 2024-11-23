package com.fiap.tech.pedidos_postech.order.queue;

import com.fiap.tech.pedidos_postech.core.queue.OrderQueueProducer;
import com.fiap.tech.pedidos_postech.domain.order.Order;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderQueueProducerImpl implements OrderQueueProducer {

    private final SqsTemplate sqsTemplate;

    @Value("${queue.pedido.name}")
    private String queueUrl;

    @Override
    public void publish(Order order) {
        sqsTemplate.send(queueUrl, order);
    }

}

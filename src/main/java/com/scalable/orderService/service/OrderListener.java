package com.scalable.orderService.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.scalable.orderService.config.RabbitMQConfig;
import com.scalable.orderService.model.Order;

@Component
public class OrderListener {

    @RabbitListener(queues = RabbitMQConfig.ORDER_QUEUE)
    public void receiveOrder(Order order) {
        //payment service
    }
}


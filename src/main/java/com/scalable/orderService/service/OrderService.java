package com.scalable.orderService.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scalable.orderService.config.RabbitMQConfig;
import com.scalable.orderService.model.Cart;
import com.scalable.orderService.model.CartItem;
import com.scalable.orderService.model.Order;
import com.scalable.orderService.model.OrderItem;
import com.scalable.orderService.model.Product;
import com.scalable.orderService.repo.CartRepository;
import com.scalable.orderService.repo.OrderRepository;
import com.scalable.orderService.repo.ProductRepo;

@Service
public class OrderService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ProductRepo productRepo;

    public void placeOrder(Long userId) {

        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

       
        Order order = new Order();
        order.setUserId(userId);
        order.setOrderDate(LocalDate.now());

        List<OrderItem> orderItems = new ArrayList<OrderItem>();
        double totalPrice = 0.0;

        for (CartItem cartItem : cart.getItems()) {
           
            Product product = productRepo.findById(cartItem.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            double itemTotal = product.getUnitPrice() * cartItem.getQuantity();
            totalPrice += itemTotal;

            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(cartItem.getProductId());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setOrder(order);

            orderItems.add(orderItem);
        }

        order.setItems(orderItems);
        order.setTotalPrice(totalPrice); 
        orderRepository.save(order);


        rabbitTemplate.convertAndSend(
                RabbitMQConfig.ORDER_EXCHANGE,
                RabbitMQConfig.ORDER_ROUTING_KEY,
                order
        );


        cart.getItems().clear();
        cartRepository.save(cart);
    }
}

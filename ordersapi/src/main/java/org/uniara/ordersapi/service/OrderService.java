package org.uniara.ordersapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uniara.ordersapi.model.Order;
import org.uniara.ordersapi.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    /*@Autowired
    private OrderProductRepository orderProductRepository;*/

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    public Optional<List<Order>> findByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public Order save(Order order) {

        /*List<OrderProduct> orderProducts = order.getOrderProducts();

        order = orderRepository.save(order);

        for (OrderProduct orderProduct : orderProducts) {
            orderProduct.setOrder(order);
            orderProductRepository.save(orderProduct);
        }*/

        return orderRepository.save(order);
    }

    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }
}

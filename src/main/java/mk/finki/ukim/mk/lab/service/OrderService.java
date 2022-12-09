package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Order;

import java.util.Optional;

public interface OrderService{
    Order save(Order order);

    Optional<Order> findById(Long id);
}

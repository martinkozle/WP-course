package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.ShoppingCart;

import java.util.List;
import java.util.Optional;

public interface OrderService{
    Order save(Order order);
    Order save(String balloonColor, String balloonSize, ShoppingCart shoppingCart);
    Optional<Order> findById(Long id);
    List<Order> findAllByShoppingCart(ShoppingCart shoppingCart);
}

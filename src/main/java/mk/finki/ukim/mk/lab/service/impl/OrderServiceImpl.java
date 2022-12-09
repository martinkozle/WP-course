package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.repository.jpa.OrderRepository;
import mk.finki.ukim.mk.lab.service.OrderService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order save(String balloonColor, String balloonSize, ShoppingCart shoppingCart) {
        var order = new Order(balloonColor, balloonSize, shoppingCart);
        if (shoppingCart.getOrders().stream().anyMatch(o -> o.equals(order))) {
            throw new IllegalArgumentException("Order already exists");
        }
        return save(order);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> findAllByShoppingCart(ShoppingCart shoppingCart) {
        return orderRepository.findAllByShoppingCart(shoppingCart);
    }
}

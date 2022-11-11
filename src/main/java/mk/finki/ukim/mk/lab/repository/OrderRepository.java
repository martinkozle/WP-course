package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {
    private final List<Order> orders;

    public OrderRepository() {
        this.orders = new ArrayList<>();
    }

    public List<Order> findAllOrders() {
        return this.orders;
    }

    public void saveOrder(Order order) {
        this.orders.add(order);
    }

    public Order findById(Long id) {
        return this.orders.stream().filter(order -> order.getOrderId().equals(id)).findFirst().orElseThrow();
    }

    public List<Order> fullSearch(String query) {
        return this.orders.stream()
                .filter(
                        order -> order.getBalloonColor().contains(query)
                                || order.getBalloonSize().contains(query)
                                || order.getClientName().contains(query)
                                || order.getClientAddress().contains(query)
                ).toList();
    }
}

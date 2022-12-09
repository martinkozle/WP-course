package mk.finki.ukim.mk.lab.repository.jpa;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByBalloonColorContainingIgnoreCaseOrBalloonSizeContainingIgnoreCase(String color, String size);
    List<Order> findAllByShoppingCart(ShoppingCart shoppingCart);
}

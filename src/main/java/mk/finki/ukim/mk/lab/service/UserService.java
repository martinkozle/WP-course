package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.model.User;

import java.time.LocalDate;
import java.util.Optional;

public interface UserService {
    User register(String username, String name, String surname, String password, LocalDate dateOfBirth);

    Optional<User> findByUsername(String username);

    Optional<ShoppingCart> getActiveShoppingCart(String username);

    Optional<Order> placeOrder(String balloonColor, String balloonSize, String username);
}

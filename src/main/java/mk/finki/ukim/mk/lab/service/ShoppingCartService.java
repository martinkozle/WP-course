package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.model.User;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartService {
    List<Order> listAllProductsInShoppingCart(Long cartId);

    ShoppingCart getActiveShoppingCart(User user);

    Optional<ShoppingCart> findLatestShoppingCartForUser(User user);

    Order addOrderToShoppingCart(User user, String balloonColor, String balloonSize);
}


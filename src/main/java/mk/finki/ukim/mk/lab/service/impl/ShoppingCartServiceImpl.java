package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.repository.jpa.ShoppingCartRepository;
import mk.finki.ukim.mk.lab.service.OrderService;
import mk.finki.ukim.mk.lab.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final OrderService orderService;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, OrderService orderService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.orderService = orderService;
    }

    @Override
    public List<Order> listAllProductsInShoppingCart(Long cartId) {
        return shoppingCartRepository.findById(cartId).orElseThrow().getOrders();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(User user) {
        return findLatestShoppingCartForUser(user).orElseGet(() -> shoppingCartRepository.save(new ShoppingCart(user)));
    }

    @Override
    public Optional<ShoppingCart> findLatestShoppingCartForUser(User user) {
        return shoppingCartRepository.findAllByUser(user).stream().max(Comparator.comparing(ShoppingCart::getDateCreated));
    }

    @Override
    public Order addOrderToShoppingCart(User user, String balloonColor, String balloonSize) {
        ShoppingCart shoppingCart = getActiveShoppingCart(user);
        return orderService.save(balloonColor, balloonSize, shoppingCart);
    }
}

package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.repository.jpa.ShoppingCartRepository;
import mk.finki.ukim.mk.lab.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public List<Order> listAllProductsInShoppingCart(Long cartId) {
        return shoppingCartRepository.findById(cartId).orElseThrow().getOrders();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(User user) {
        return shoppingCartRepository.findFirstByUserOrderByDateCreatedDesc(user).orElse(
                shoppingCartRepository.save(new ShoppingCart(user))
        );
    }

    @Override
    public Optional<ShoppingCart> findLatestShoppingCartForUser(User user) {
        return shoppingCartRepository.findFirstByUserOrderByDateCreatedDesc(user);
    }

    @Override
    public ShoppingCart addOrderToShoppingCart(User user, Order order) {
        ShoppingCart shoppingCart = getActiveShoppingCart(user);
        if (shoppingCart.getOrders().stream().anyMatch(o -> o.equals(order))) {
            throw new IllegalArgumentException("Order already exists");
        }
        shoppingCart.getOrders().add(order);
        return this.shoppingCartRepository.save(shoppingCart);

    }
}

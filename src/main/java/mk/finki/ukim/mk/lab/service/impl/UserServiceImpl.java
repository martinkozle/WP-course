package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.repository.jpa.UserRepository;
import mk.finki.ukim.mk.lab.service.OrderService;
import mk.finki.ukim.mk.lab.service.ShoppingCartService;
import mk.finki.ukim.mk.lab.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ShoppingCartService shoppingCartService;
    private final OrderService orderService;

    public UserServiceImpl(UserRepository userRepository, ShoppingCartService shoppingCartService, OrderService orderService) {
        this.userRepository = userRepository;
        this.shoppingCartService = shoppingCartService;
        this.orderService = orderService;
    }

    @Override
    public User register(String username, String name, String surname, String password, LocalDate dateOfBirth) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("User already exists");
        }
        return userRepository.save(new User(username, name, surname, password, dateOfBirth));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<ShoppingCart> getActiveShoppingCart(String username) {
        var user = findByUsername(username);
        if (user.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(shoppingCartService.getActiveShoppingCart(user.get()));
    }

    @Override
    public Optional<Order> placeOrder(String balloonColor, String balloonSize, String username) {
        var user = findByUsername(username);
        if (user.isEmpty()) {
            return Optional.empty();
        }
        ShoppingCart shoppingCart = shoppingCartService.getActiveShoppingCart(user.get());
        Order order = new Order(balloonColor, balloonSize, shoppingCart);
        orderService.save(order);
        return Optional.of(order);
    }
}

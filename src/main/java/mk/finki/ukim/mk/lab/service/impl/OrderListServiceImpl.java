package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.repository.jpa.OrderRepository;
import mk.finki.ukim.mk.lab.service.OrderListService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderListServiceImpl implements OrderListService {
    private final OrderRepository orderRepository;

    public OrderListServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> listAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> fullSearch(String query) {
        return orderRepository.findAllByBalloonColorContainingIgnoreCaseOrBalloonSizeContainingIgnoreCase(query, query);
    }
}

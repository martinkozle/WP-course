package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Order;

import java.util.List;

public interface OrderListService {
    List<Order> listAll();

    List<Order> fullSearch(String query);
}

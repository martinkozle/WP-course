package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.service.*;
import org.hibernate.Hibernate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
public class BalloonController {
    private final BalloonService balloonService;
    private final ManufacturerService manufacturerService;
    private final OrderListService orderListService;
    private final ShoppingCartService shoppingCartService;
    private final OrderService orderService;

    public BalloonController(BalloonService balloonService, ManufacturerService manufacturerService, OrderListService orderListService, ShoppingCartService shoppingCartService, OrderService orderService) {
        this.balloonService = balloonService;
        this.manufacturerService = manufacturerService;
        this.orderListService = orderListService;
        this.shoppingCartService = shoppingCartService;
        this.orderService = orderService;
    }

    @GetMapping("/balloons")
    public String getBalloonsPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Balloon> balloons = balloonService.listAll();
        model.addAttribute("balloons", balloons);
        return "listBalloons";
    }

    @PostMapping("/balloons/add")
    public String saveBalloon(@RequestParam String name, @RequestParam String description, @RequestParam Long manufacturerId) {
        balloonService.save(name, description, manufacturerId);
        return "redirect:/balloons";
    }

    @GetMapping("/balloons/delete/{id}")
    public String deleteBalloonConfirmation(@PathVariable Long id, Model model) {
        Optional<Balloon> balloon = balloonService.findById(id);
        if (balloon.isEmpty()) {
            return "redirect:/balloons?error=Balloon+not+found";
        }
        model.addAttribute("balloon", balloon.get());
        return "delete-balloon";
    }

    @DeleteMapping("/balloons/delete/{id}")
    public String deleteBalloon(@PathVariable Long id) {
        balloonService.deleteById(id);
        return "redirect:/balloons";
    }

    @GetMapping("/balloons/edit-form/{id}")
    public String getEditBalloonPage(@PathVariable Long id, Model model) {
        Optional<Balloon> balloon = balloonService.findById(id);
        if (balloon.isEmpty()) {
            return "redirect:/balloons?error=Balloon+not+found";
        }

        model.addAttribute("balloon", balloon.get());
        model.addAttribute("manufacturers", manufacturerService.findAll());
        return "add-balloon";
    }

    @GetMapping("/balloons/add-form")
    public String getAddBalloonPage(Model model) {
        model.addAttribute("balloon", null);
        model.addAttribute("manufacturers", manufacturerService.findAll());
        return "add-balloon";
    }

    @GetMapping("/userOrders")
    public String getUserOrdersPage(Model model) {
        var user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var cart = shoppingCartService.getActiveShoppingCart(user);
        var orders = orderService.findAllByShoppingCart(cart);
        model.addAttribute("orders", orders);
        model.addAttribute("query", "");
        return "listOrders";
    }

    @GetMapping("/orders")
    public String getAllOrdersPage(Model model) {
        List<Order> orders = orderListService.listAll();
        Hibernate.initialize(orders);
        for (Order order : orders) {
            Hibernate.initialize(order.getShoppingCart());
            Hibernate.initialize(order.getShoppingCart().getUser());
        }
        System.out.println(orders);
        model.addAttribute("orders", orders);
        model.addAttribute("query", "");
        return "listOrders";
    }

    @PostMapping("/orders")
    public String getFilteredOrdersPage(@RequestParam String query, Model model) {
        List<Order> orders = orderListService.fullSearch(query);
        model.addAttribute("orders", orders);
        model.addAttribute("query", "");
        return "listOrders";
    }
}

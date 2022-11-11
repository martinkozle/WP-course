package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.service.BalloonService;
import mk.finki.ukim.mk.lab.service.ManufacturerService;
import mk.finki.ukim.mk.lab.service.OrderListService;
import mk.finki.ukim.mk.lab.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class BalloonController {
    private final BalloonService balloonService;
    private final ManufacturerService manufacturerService;
    private final OrderService orderService;
    private final OrderListService orderListService;

    public BalloonController(BalloonService balloonService, ManufacturerService manufacturerService, OrderService orderService, OrderListService orderListService) {
        this.balloonService = balloonService;
        this.manufacturerService = manufacturerService;
        this.orderService = orderService;
        this.orderListService = orderListService;
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
        Balloon balloon = balloonService.findById(id);
        model.addAttribute("balloon", balloon);
        return "delete-balloon";
    }

    @DeleteMapping("/balloons/delete/{id}")
    public String deleteBalloon(@RequestParam Long id) {
        balloonService.deleteById(id);
        return "redirect:/balloons";
    }

    @GetMapping("/balloons/edit-form/{id}")
    public String getEditBalloonPage(@PathVariable Long id, Model model) {
        Balloon balloon = null;
        try {
            balloon = balloonService.findById(id);
        } catch (NoSuchElementException e) {
            return "redirect:/balloons?error=Balloon+not+found";
        }
        model.addAttribute("balloon", balloon);
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
    public String getUserOrdersPage(HttpSession session, Model model) {
        List<Long> userOrderIds = (List<Long>) session.getAttribute("userOrders");
        if (userOrderIds == null) {
            userOrderIds = List.of();
        }
        List<Order> userOrders = userOrderIds.stream().map(orderService::findById).toList();
        model.addAttribute("orders", userOrders);
        return "listOrders";
    }

    @GetMapping("/orders")
    public String getAllOrdersPage(Model model) {
        List<Order> orders = orderListService.listAll();
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

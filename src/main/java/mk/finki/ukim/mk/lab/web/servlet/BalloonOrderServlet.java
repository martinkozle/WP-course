package mk.finki.ukim.mk.lab.web.servlet;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.service.OrderService;
import mk.finki.ukim.mk.lab.service.ShoppingCartService;
import mk.finki.ukim.mk.lab.service.UserService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "balloon-order-servlet", urlPatterns = "/BalloonOrder.do")
public class BalloonOrderServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final UserService userService;

    public BalloonOrderServlet(SpringTemplateEngine springTemplateEngine, UserService userService) {
        this.springTemplateEngine = springTemplateEngine;
        this.userService = userService;
    }

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws java.io.IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        HttpSession session = req.getSession();
        String color = (String) session.getAttribute("color");
        String size = (String) session.getAttribute("size");
        context.setVariable("color", color);
        context.setVariable("size", size);
        resp.setContentType("application/xhtml+xml");
        this.springTemplateEngine.process("deliveryInfo.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws javax.servlet.ServletException, java.io.IOException {
        String clientName = req.getParameter("clientName");
        String clientAddress = req.getParameter("clientAddress");
        HttpSession session = req.getSession();
        String color = (String) session.getAttribute("color");
        String size = (String) session.getAttribute("size");
        session.setAttribute("clientName", clientName);
        session.setAttribute("clientAddress", clientAddress);
        userService.placeOrder(color, size, "testuser");

        resp.sendRedirect("/ConfirmationInfo");
    }
}

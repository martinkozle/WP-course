package mk.finki.ukim.mk.lab.web.servlet;

import mk.finki.ukim.mk.lab.service.OrderListService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name="order-list-servlet", urlPatterns="/orderList")
public class OrderListServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final OrderListService orderListService;

    public OrderListServlet(SpringTemplateEngine springTemplateEngine, OrderListService orderListService) {
        this.springTemplateEngine = springTemplateEngine;
        this.orderListService = orderListService;
    }

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws java.io.IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("orders", orderListService.listAll());
        resp.setContentType("application/xhtml+xml");
        this.springTemplateEngine.process("listOrders.html", context, resp.getWriter());
    }
}

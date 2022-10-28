package mk.finki.ukim.mk.lab.web.servlet;

import mk.finki.ukim.mk.lab.service.OrderService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

@WebServlet(name = "balloon-order-servlet", urlPatterns = "/BalloonOrder.do")
public class BalloonOrderServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final OrderService orderService;

    public BalloonOrderServlet(SpringTemplateEngine springTemplateEngine, OrderService orderService) {
        this.springTemplateEngine = springTemplateEngine;
        this.orderService = orderService;
    }

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws javax.servlet.ServletException, java.io.IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        HttpSession session = req.getSession();
        String color = (String) session.getAttribute("color");
        String size = (String) session.getAttribute("size");
        if (color == null) {
            resp.sendRedirect("/");
        } else if (size == null) {
            resp.sendRedirect("/selectBalloon");
        }
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
        this.orderService.placeOrder(color, size, clientName, clientAddress);

        session.setAttribute("clientName", clientName);
        session.setAttribute("clientAddress", clientAddress);

        resp.sendRedirect("/ConfirmationInfo");
    }
}

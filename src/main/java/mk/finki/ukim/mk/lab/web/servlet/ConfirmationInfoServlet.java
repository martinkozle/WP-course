package mk.finki.ukim.mk.lab.web.servlet;

import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

@WebServlet(name = "confirmation-info-servlet", urlPatterns = "/ConfirmationInfo")
public class ConfirmationInfoServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;

    public ConfirmationInfoServlet(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws javax.servlet.ServletException, java.io.IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        HttpSession session = req.getSession();
        String clientName = (String) session.getAttribute("clientName");
        String clientAddress = (String) session.getAttribute("clientAddress");
        String color = (String) session.getAttribute("color");
        String size = (String) session.getAttribute("size");
        if (color == null) {
            resp.sendRedirect("/");
        } else if (size == null) {
            resp.sendRedirect("/selectBalloon");
        } else if (clientName == null || clientAddress == null) {
            resp.sendRedirect("/BalloonOrder.do");
        }
        req.setAttribute("clientName", clientName);
        req.setAttribute("clientAddress", clientAddress);
        req.setAttribute("color", color);
        req.setAttribute("size", size);
        req.setAttribute("clientIpAddress", req.getRemoteAddr());
        req.setAttribute("clientBrowser", req.getHeader("User-Agent"));
        resp.setContentType("application/xhtml+xml");
        this.springTemplateEngine.process("confirmationInfo.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws javax.servlet.ServletException, java.io.IOException {
        String clientName = req.getParameter("clientName");
        String clientAddress = req.getParameter("clientAddress");
        HttpSession session = req.getSession();
        session.setAttribute("clientName", clientName);
        session.setAttribute("clientAddress", clientAddress);
        resp.sendRedirect("/ConfirmationInfo");
    }
}

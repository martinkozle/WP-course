package mk.finki.ukim.mk.lab.web.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "log-out-servlet", urlPatterns = "/logoutOrder")
public class LogOutServlet extends HttpServlet {
    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws javax.servlet.ServletException, java.io.IOException {
        req.getSession().invalidate();
        resp.sendRedirect("/");
    }
}

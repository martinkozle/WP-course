package mk.finki.ukim.mk.lab.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@WebFilter
public class BalloonFilter implements Filter {
    private final Logger logger = Logger.getLogger(BalloonFilter.class.getName());
    private final List<String> colorRequiredPaths = List.of("/selectBalloon", "/BalloonOrder.do", "ConfirmationInfo");
    private final List<String> sizeRequiredPaths = List.of("/BalloonOrder.do", "/ConfirmationInfo");
    private final List<String> clientRequiredPaths = List.of("/ConfirmationInfo");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String color = (String) req.getSession().getAttribute("color");
        String size = (String) req.getSession().getAttribute("size");
        String clientName = (String) req.getSession().getAttribute("clientName");
        String clientAddress = (String) req.getSession().getAttribute("clientAddress");

        // log with spring logger method and uri
        logger.info(String.format("Request %s %s", req.getMethod(), req.getRequestURI()));

        if (req.getMethod().equals("GET")) {
            String path = req.getServletPath();
            if (colorRequiredPaths.contains(path) && color == null) {
                resp.sendRedirect("/balloons");
            } else if (sizeRequiredPaths.contains(path) && size == null) {
                resp.sendRedirect("/selectBalloon");
            } else if (clientRequiredPaths.contains(path) && (clientName == null || clientAddress == null)) {
                resp.sendRedirect("/BalloonOrder.do");
            } else {
                filterChain.doFilter(req, resp);
            }
        } else {
            filterChain.doFilter(req, resp);
        }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

package ru.job4j.carplace.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The type Login filter.
 */
@lombok.extern.log4j.Log4j2
@WebFilter(
        urlPatterns = "/*"
)
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        if (request.getRequestURI().contains("/login")) {
            chain.doFilter(req, resp);
        } else {
            final HttpSession session = request.getSession();
            if (session.getAttribute("login") == null) {
                ((HttpServletResponse) resp).sendRedirect("login");
                return;
            }
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {
    }
}
